package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import peaksoft.config.HBConfig;
import peaksoft.dao.AddressDao;
import peaksoft.entities.Address;
import peaksoft.entities.Agency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressDaoImpl implements AddressDao {
    private final EntityManagerFactory entityManagerFactory = HBConfig.getEntityManagerFactory();

    public Map<Address, Agency> getAddressById(Long id) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, id);

            Map<Address, Agency> addressMap = new HashMap<Address, Agency>();
            addressMap.put(address,address.getAgency());

            entityManager.getTransaction().commit();
            return addressMap;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } return null;
    }

    @Override
    public int getAgencyCountByCity(String city) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Address address = entityManager
                    .createQuery("from Address where city = :city", Address.class)
                    .setParameter("city", city)
                    .getSingleResult();

            List<Agency> agencies = entityManager
                    .createQuery("select a from Agency a", Agency.class)
                    .getResultList();

            int counter = 0;
            for (Agency agency : agencies) {
                if (agency.getAddress().equals(address)) {
                    counter++;
                }
            }
            return counter;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public List<Address> getAllAddress() {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from Address", Address.class);
            return query.getResultList();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }return null;
    }

    @Override
    public String updateAddress(Address address, Long id) {
       try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
           entityManager.getTransaction().begin();
           Address newAddress = entityManager.find(Address.class, id);
           newAddress.setCity(address.getCity());
           newAddress.setRegion(address.getRegion());
           newAddress.setStreet(address.getStreet());
           entityManager.merge(newAddress);
           entityManager.getTransaction().commit();
           return "successfully updated";
       }catch (Exception e) {
           System.out.println(e.getMessage());
       }
        return "";
    }

    @Override
    public void getAllAddressesWithAgencies() {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("from Address", Address.class );
            List<Address> addresses = query.getResultList();
            for(Address address : addresses) {
                System.out.println(address);

            Agency agency = address.getAgency();
            if (agency != null) {
                System.out.println(agency);
            }
                System.out.println("---------------------------------------------------------------");
            }
            entityManager.getTransaction().commit();
        }
    }
}
