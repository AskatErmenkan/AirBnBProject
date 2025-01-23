package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.HBConfig;
import peaksoft.dao.CustomerDao;
import peaksoft.entities.Agency;
import peaksoft.entities.Customer;
import peaksoft.entities.House;
import peaksoft.entities.RentInfo;
import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private final EntityManagerFactory emf = HBConfig.getEntityManagerFactory();

    @Override
    public void saveCustomer(Customer customer) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            System.out.println("Customer saved");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Customer saveCustomerWithRentInfo(Customer customer, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();

            House house = entityManager.find(House.class, houseId);
            if (house == null) {
                throw new IllegalArgumentException("Дом с ID " + houseId + " не найден");
            }

            Agency agency = entityManager.find(Agency.class, agencyId);
            if (agency == null) {
                throw new IllegalArgumentException("Агентство с ID " + agencyId + " не найдено");
            }

            RentInfo rentInfo = new RentInfo();
            rentInfo.setCheckIn(checkIn);
            rentInfo.setCheckOut(checkOut);
            rentInfo.setCustomer(customer);
            rentInfo.setHouse(house);
            agency.getRentInfos().add(rentInfo);

            customer.getRentInfo().add(rentInfo);

            entityManager.persist(customer);

            entityManager.getTransaction().commit();
            return customer;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Customer getCustomerById(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.getTransaction().commit();
            return customer;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public List<Customer> getAllCustomers() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<Customer> customers = entityManager.createQuery("select c from Customer c").getResultList();
            entityManager.getTransaction().commit();
            return customers;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            Customer oldCustomer = entityManager.find(Customer.class, id);
            oldCustomer.setFirstName(customer.getFirstName());
            oldCustomer.setLastName(customer.getLastName());
            oldCustomer.setEmail(customer.getEmail());
            oldCustomer.setDateOfBirth(customer.getDateOfBirth());
            oldCustomer.setGender(customer.getGender());
            oldCustomer.setNationality(customer.getNationality());
            oldCustomer.setFamilyStatus(customer.getFamilyStatus());
            entityManager.merge(oldCustomer);
            entityManager.getTransaction().commit();
            System.out.println("Customer " + customer.getFirstName() + " обновлен");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();

            Customer customer = entityManager.find(Customer.class, id);
            if (customer == null) {
                throw new IllegalArgumentException("Customer с ID " + id + " не найден");
            }

            boolean hasActiveRent = customer.getRentInfo().stream()
                    .anyMatch(rent -> rent.getCheckOut().isAfter(LocalDate.now()));

            if (hasActiveRent) {
                throw new IllegalStateException("Customer не может быть удалён, так как у него есть активные аренды");
            }

            customer.getRentInfo().forEach(entityManager::remove);

            entityManager.remove(customer);

            entityManager.getTransaction().commit();
            System.out.println("Customer с ID " + id + " удалён");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
