package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import peaksoft.config.HBConfig;
import peaksoft.dao.AgencyDao;
import peaksoft.entities.Address;
import peaksoft.entities.Agency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgencyDaoImpl implements AgencyDao {
    private final EntityManagerFactory emf = HBConfig.getEntityManagerFactory();

    @Override
    public void saveAgency(Agency agency) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
               if (agency.getAddress() != null) {
                   agency.getAddress().setAgency(agency);
               }
                entityManager.persist(agency);
                entityManager.getTransaction().commit();
                System.out.println("Agency and Address saved");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Agency getAgencyById(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, id);
            entityManager.getTransaction().commit();
            return agency;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Agency> getAllAgencies() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<Agency> agencies = entityManager.createQuery("from Agency").getResultList();
            entityManager.getTransaction().commit();
            return agencies;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAgencyById(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, id);
            entityManager.remove(agency);
            entityManager.getTransaction().commit();
            System.out.println("Agency deleted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateAgency(Agency agency, Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            Agency newAgency = entityManager.find(Agency.class, id);
            newAgency.setName(agency.getName());
            newAgency.setAddress(agency.getAddress());
            newAgency.setPhoneNumber(agency.getPhoneNumber());
            entityManager.getTransaction().commit();
            System.out.println("Agency updated");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<String, List<Agency>> getAgenciesGroupedByRegion() {
        Map<String, List<Agency>> groupByRegion = new HashMap<>();
        try (EntityManager entityManager = emf.createEntityManager()) {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from Address", Address.class);
            List<Address> addresses = query.getResultList();
            for (Address address : addresses) {
                String region = address.getRegion();
                if (region != null) {
                    List<Agency> agencies = groupByRegion.getOrDefault(region, new ArrayList<>());
                    Agency agency = address.getAgency();
                    if (agency != null) {
                        agencies.add(agency);
                    }
                    groupByRegion.put(region, agencies);
                }
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return groupByRegion;
    }
}

