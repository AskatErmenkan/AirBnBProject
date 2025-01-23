package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.HBConfig;
import peaksoft.dao.RentInfoDao;
import peaksoft.entities.RentInfo;

import java.time.LocalDate;
import java.util.List;

public class RentInfoDaoImpl implements RentInfoDao {

    private final EntityManagerFactory emf = HBConfig.getEntityManagerFactory();

    @Override
    public void saveRentInfo(RentInfo rentInfo) {
//        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
//            entityManager.getTransaction().begin();
//            entityManager.persist(rentInfo);
//            entityManager.getTransaction().commit();
//            System.out.println("RentInfo сохранен");
//        } catch (HibernateException e) {
//            System.out.println(e.getMessage());
//        }
    }

    @Override
    public RentInfo getRentInfoById(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            RentInfo rentInfo = entityManager.find(RentInfo.class, id);
            entityManager.getTransaction().commit();
            return rentInfo;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<RentInfo> getAllRentInfos() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<RentInfo> rentInfos = entityManager.createQuery("select r from RentInfo r", RentInfo.class).getResultList();
            entityManager.getTransaction().commit();
            return rentInfos;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateRentInfo(Long id, RentInfo rentInfo) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            RentInfo oldRentInfo = entityManager.find(RentInfo.class, id);
            oldRentInfo.setCheckIn(rentInfo.getCheckIn());
            oldRentInfo.setCheckOut(rentInfo.getCheckOut());
            entityManager.merge(oldRentInfo);
            entityManager.getTransaction().commit();
            System.out.println("RentInfo обновлен");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteRentInfo(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            RentInfo rentInfo = entityManager.find(RentInfo.class, id);
            entityManager.remove(rentInfo);
            entityManager.getTransaction().commit();
            System.out.println("RentInfo удален");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<RentInfo> getRentInfosBetweenDates(LocalDate startDate, LocalDate endDate) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager.createQuery(
                            "select r from RentInfo r " +
                                    "join fetch r.owner " +
                                    "join fetch r.house " +
                                    "join fetch r.customer " +
                                    "where r.checkIn >= :startDate and r.checkOut <= :endDate", RentInfo.class
                    ).setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public int countRentedHousesByAgency(Long agencyId, LocalDate date) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            Long count = entityManager.createQuery(
                            "select count(r) from RentInfo r join r.house h join h.address a " +
                                    "where a.agency.id = :agencyId and r.checkIn <= :date and r.checkOut >= :date", Long.class
                    ).setParameter("agencyId", agencyId)
                    .setParameter("date", date)
                    .getSingleResult();
            return count.intValue();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
