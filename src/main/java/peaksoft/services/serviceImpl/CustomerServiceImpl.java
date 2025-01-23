package peaksoft.services.serviceImpl;

import peaksoft.dao.CustomerDao;
import peaksoft.dao.daoImpl.CustomerDaoImpl;
import peaksoft.entities.Customer;
import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;
import peaksoft.services.CustomerService;

import java.time.LocalDate;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();


    @Override
    public void saveCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
    }

    @Override
    public Customer saveCustomerWithRentInfo(Customer customer, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
        customerDao.saveCustomerWithRentInfo(customer, houseId, agencyId, checkIn, checkOut);
        return null;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return List.of();
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {

    }

    @Override
    public void deleteCustomer(Long id) {

    }
}
