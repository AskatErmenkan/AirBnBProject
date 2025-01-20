package peaksoft;

import peaksoft.dao.AddressDao;
import peaksoft.dao.CustomerDao;
import peaksoft.dao.daoImpl.AddressDaoImpl;
import peaksoft.entities.Address;
import peaksoft.entities.Agency;
import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;
import peaksoft.services.AddressService;
import peaksoft.services.AgencyService;
import peaksoft.services.CustomerService;
import peaksoft.services.serviceImpl.AddressServiceImpl;
import peaksoft.services.serviceImpl.AgencyServiceImpl;
import peaksoft.services.serviceImpl.CustomerServiceImpl;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class App
{
    public static void main( String[] args ) {

//        HBConfig.getEntityManagerFactory();
        AddressDao addressDao = new AddressDaoImpl();
        AgencyService agencyService = new AgencyServiceImpl();
        AddressService addressService = new AddressServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();

//          TODO Баардык агентстволорду ID менен чыгаруу.
//        System.out.println(agencyService.getAgencyById(1L));

//          TODO  Agency тузулуп жатканда адреси кошо тузулсун!
//       Agency agency = new Agency("Jamaika", "+996500500100");
//       Address address = new Address("Karakol", "Ysyk-Kol", "Balbai 31");
//       address.setAgency(agency);
//       agency.setAddress(address);
//       agencyService.saveAgency(agency);
//
//           TODO  Agency очкондо адреси жана rent_info кошо очсун
//        agencyService.deleteAgency(1L);

//          TODO Баардык агентстволорду ID менен чыгаруу.
//        System.out.println(addressService.getAddressById(1L));

//          TODO Update method Address.
//        addressService.updateAddress(1L,new Address("Kant", "Chui", "Tailak"));

//        TODO Колдонуучу бир шаардын атын жазса ошол шаарда
//         канча агентсво бар экенин эсептеп чыгарсын
//        System.out.println(addressService.getAgencyCountByCity("Bishkek"));

//          TODO Бир метод ар бир адрести агентсвосу менен чыгарсын..
//        addressService.getAllAddressesWithAgencies();

//          TODO Ар бир регион жана ошол региондун агентсволары баары чыксын
//        agencyService.getAgenciesGroupedByRegion(); //Караш керек дагы

//          TODO CreateCustomer method
//        customerService.saveCustomer("Baian", "Akmatov", "baian@gmail.com",
//                LocalDate.of (1987,10,22), Gender.MALE, "Kyrgyz", FamilyStatus.MARRIED);














    }
}
