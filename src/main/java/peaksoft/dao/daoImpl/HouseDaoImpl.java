package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.HBConfig;
import peaksoft.dao.HouseDao;
import peaksoft.entities.House;
import peaksoft.entities.Owner;

import java.time.LocalDate;
import java.util.List;

public class HouseDaoImpl  implements HouseDao {
    private final EntityManagerFactory emf = HBConfig.getEntityManagerFactory();

    @Override
    public void saveHouse(House house, Long ownerId) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Owner owner = em.find(Owner.class, ownerId);
            house.setOwner(owner);
            if(owner == null) {
                throw new IllegalArgumentException("Owner с ID " + ownerId + " не найден");
            }house.setOwner(owner);
            owner.getHouses().add(house);
            em.persist(house);
            em.getTransaction().commit();
            System.out.println("House saved and associate it with an Owner ");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public House getHouseById(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, id);
            entityManager.getTransaction().commit();
            return house;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<House> getAllHouses() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<House> houses = entityManager.createQuery("select h from House h", House.class).getResultList();
            entityManager.getTransaction().commit();
            return houses;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateHouse(Long id, House house) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            House oldHouse = entityManager.find(House.class, id);
            oldHouse.setHouseType(house.getHouseType());
            oldHouse.setPrice(house.getPrice());
            oldHouse.setRating(house.getRating());
            oldHouse.setDescription(house.getDescription());
            oldHouse.setRoom(house.getRoom());
            oldHouse.setFurniture(house.getFurniture());
            entityManager.merge(oldHouse);
            entityManager.getTransaction().commit();
            System.out.println("House обновлен");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteHouse(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, id);
            if (house == null) {
                throw new IllegalArgumentException("House с ID " + id + " не найден");
            }

            boolean hasActiveRent = house.getRentInfo() != null &&
                    house.getRentInfo().getCheckOut().isAfter(LocalDate.now());

            if (hasActiveRent) {
                throw new IllegalStateException("Невозможно удалить дом с активной арендой");
            }

            entityManager.remove(house);
            entityManager.getTransaction().commit();
            System.out.println("House удалён");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<House> getHousesByRegion(String region) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager.createQuery(
                    "select h from House h where h.address.region = :region", House.class
            ).setParameter("region", region).getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<House> getHousesByAgencyId(Long agencyId) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager.createQuery(
                    "select h from House h join h.address a where a.agency.id = :agencyId", House.class
            ).setParameter("agencyId", agencyId).getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<House> getHousesByOwnerId(Long ownerId) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager.createQuery(
                    "select h from House h where h.owner.id = :ownerId", House.class
            ).setParameter("ownerId", ownerId).getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<House> getHousesBetweenDates(LocalDate startDate, LocalDate endDate) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager.createQuery(
                            "select h from House h join h.rentInfo r where r.checkIn >= :startDate and r.checkOut <= :endDate", House.class
                    ).setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
