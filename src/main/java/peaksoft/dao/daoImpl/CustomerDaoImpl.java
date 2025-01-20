package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.HBConfig;
import peaksoft.dao.CustomerDao;
import peaksoft.entities.Customer;
import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;

import java.time.LocalDate;

public class CustomerDaoImpl implements CustomerDao {
    private final EntityManagerFactory emf = HBConfig.getEntityManagerFactory();

    @Override
    public void saveCustomer(String firstName, String lastName, String email, LocalDate dateOfBirth, Gender gender, String nationality, FamilyStatus familyStatus) {
        try(EntityManager em = emf.createEntityManager()) {
            Customer customer = new Customer(firstName, lastName, email, dateOfBirth, gender, nationality);
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            System.out.println("Customer saved");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
