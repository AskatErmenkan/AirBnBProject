package peaksoft.dao;

import peaksoft.entities.Customer;
import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public interface CustomerDao {
    void saveCustomer(Customer customer);

    Customer saveCustomerWithRentInfo(Customer customer, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut);

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

    void updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);

}


