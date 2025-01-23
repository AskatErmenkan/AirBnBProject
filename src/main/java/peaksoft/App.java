package peaksoft;

import peaksoft.dao.AddressDao;
import peaksoft.dao.CustomerDao;
import peaksoft.dao.daoImpl.AddressDaoImpl;
import peaksoft.entities.*;
import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;
import peaksoft.enums.HouseType;
import peaksoft.services.*;
import peaksoft.services.serviceImpl.*;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class App
{
    public static void main( String[] args ) {

//        HBConfig.getEntityManagerFactory();
        AgencyService agencyService = new AgencyServiceImpl();
        AddressService addressService = new AddressServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        HouseService houseService = new HouseServiceImpl();
        OwnerService ownerService = new OwnerServiceImpl();

//                              TODO     AGENCY
//          TODO Баардык агентстволорду ID менен чыгаруу.
//        System.out.println(agencyService.getAgencyById(1L));

//          TODO  Agency тузулуп жатканда адреси кошо тузулсун!
//       Agency agency = new Agency("GAMSUMO", "+996999604050");
//       Address address = new Address("Bishkek", "Chui", "Manas prospecti");
//       address.setAgency(agency);
//       agency.setAddress(address);
//       agencyService.saveAgency(agency);
//                  TODO  Agency очкондо адреси жана rent_info кошо очсун
//        agencyService.deleteAgency(1L);
//                  TODO Баардык агентстволорду ID менен чыгаруу.
//        System.out.println(addressService.getAddressById(1L));
//
//                              TODO ADDRESS
//          TODO Update method Address.
//        addressService.updateAddress(1L,new Address("Kant", "Chui", "Tailak baatyr"));
//          TODO Колдонуучу бир шаардын атын жазса ошол шаарда
//               канча агентсво бар экенин эсептеп чыгарсын
//        System.out.println(addressService.getAgencyCountByCity("Bishkek"));

//          TODO Бир метод ар бир адрести агентсвосу менен чыгарсын..
//        addressService.getAllAddressesWithAgencies();
//                  TODO Ар бир регион жана ошол региондун агентсволары баары чыксын
//        System.out.println(addressService.getAgenciesGroupedByRegion()); //Караш керек дагы
//
//
//          TODO CreateCustomer method
//        customerService.saveCustomer(new Customer("Asyla", "Saalieva", "asyla@gmail.com",
//                LocalDate.of (1999,10,11), Gender.FEMALE, "Kyrgyz", FamilyStatus.MARRIED));

//       TODO Customer уйду ижарага алса болот. Ижарага алып жатканда customer id, house id, agency id жана check in check out жазышы керек.
//       customerService.saveCustomerWithRentInfo(new Customer("Nura","Baetova","nura@gmail.com", LocalDate.of(1995, 4, 8), Gender.FEMALE, "Kyrgyz", FamilyStatus.SINGLE), 6L, 7L,  LocalDate.of(2025, 1 ,10), LocalDate.of(2025, 1, 17));

//          TODO SaveHouse method
        houseService.saveHouse(new House(HouseType.VILLA, BigDecimal.valueOf( 3500), 4.7, "Villa в центре находится", 5, true), 1L);

//          TODO SaveOwner method
//        ownerService.saveOwner(new Owner("Mirlan", "Tashtanbekov", "mirlan@gmail.com", LocalDate.of(1992, 5, 14),Gender.MALE));
//        ownerService.saveOwner(new Owner("Aigul", "Baeva", "aigul@gmail.com", LocalDate.of(2002, 12, 18),Gender.FEMALE));












    }
}
