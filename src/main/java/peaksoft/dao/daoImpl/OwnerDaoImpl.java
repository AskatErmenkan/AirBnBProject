package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.HBConfig;
import peaksoft.dao.OwnerDao;
import peaksoft.entities.Agency;
import peaksoft.entities.House;
import peaksoft.entities.Owner;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

public class OwnerDaoImpl  implements OwnerDao {
    private final EntityManagerFactory emf = HBConfig.getEntityManagerFactory();
    @Override
    public void saveOwner(Owner owner) {
        if (Period.between(owner.getDateOfBirth(), LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("Возраст Owner должен быть не меньше 18 лет");
        }

        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
            System.out.println("Owner " + owner.getFirstName() + " сохранен");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveOwnerWithHouse(Owner owner, House house) {
        if (Period.between(owner.getDateOfBirth(), LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("Возраст Owner должен быть не меньше 18 лет");
        }
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            owner.getHouses().add(house);
            house.setOwner(owner);
            entityManager.persist(owner);
            entityManager.persist(house);
            entityManager.getTransaction().commit();
            System.out.println("Owner с домом сохранён");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Owner getOwnerById(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, id);
            entityManager.getTransaction().commit();
            return owner;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Owner> getAllOwners() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<Owner> owners = entityManager.createQuery("select o from Owner o", Owner.class).getResultList();
            entityManager.getTransaction().commit();
            return owners;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateOwner(Long id, Owner owner) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            Owner oldOwner = entityManager.find(Owner.class, id);
            oldOwner.setFirstName(owner.getFirstName());
            oldOwner.setLastName(owner.getLastName());
            oldOwner.setEmail(owner.getEmail());
            oldOwner.setDateOfBirth(owner.getDateOfBirth());
            oldOwner.setGender(owner.getGender());
            entityManager.merge(oldOwner);
            entityManager.getTransaction().commit();
            System.out.println("Owner обновлен");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteOwner(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, id);

            if (owner.getHouses() != null) {
                for (House house : owner.getHouses()) {
                    if (house.getAddress() != null && house.getAddress().getAgency() != null) {
                        System.out.println("Дом " + house.getId() + " не может быть удален, так как он привязан к агентству");
                        continue;
                    }
                    entityManager.remove(house);
                }
            }
            entityManager.remove(owner);
            entityManager.getTransaction().commit();
            System.out.println("Owner удалён");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void assignToAgency(Long ownerId, Long agencyId) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, ownerId);
            Agency agency = entityManager.find(Agency.class, agencyId);

            if (!owner.getAgencies().contains(agency)) {
                owner.getAgencies().add(agency);
                agency.getOwner().add(owner);
            }
            entityManager.getTransaction().commit();
            System.out.println("Owner привязан к Agency");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> listOwnersWithAges() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager.createQuery(
                    "select concat(o.firstName, ' ', o.lastName, ' - ', (year(current_date) - year(o.dateOfBirth))) " +
                            "from Owner o", String.class
            ).getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Owner> getOwnersByAgencyId(Long agencyId) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager.createQuery(
                    "select o from Owner o join o.agencies a where a.id = :agencyId", Owner.class
            ).setParameter("agencyId", agencyId).getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }
}
