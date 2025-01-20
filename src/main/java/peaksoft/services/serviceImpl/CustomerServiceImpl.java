package peaksoft.services.serviceImpl;

import peaksoft.dao.CustomerDao;
import peaksoft.dao.daoImpl.CustomerDaoImpl;
import peaksoft.entities.Customer;
import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;
import peaksoft.services.CustomerService;

import java.time.LocalDate;
import java.util.Date;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void saveCustomer(String firstName, String lastName, String email, LocalDate dateOfBirth, Gender gender, String nationality, FamilyStatus familyStatus) {
    customerDao.saveCustomer(firstName, lastName, email, dateOfBirth, gender, nationality, familyStatus);
    }
}
