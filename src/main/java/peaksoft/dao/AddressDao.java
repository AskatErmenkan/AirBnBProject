package peaksoft.dao;

import peaksoft.entities.Address;
import peaksoft.entities.Agency;

import java.util.List;
import java.util.Map;

public interface AddressDao {
    Map<Address, Agency> getAddressById(Long id);
    int getAgencyCountByCity(String city);
    List<Address> getAllAddress();
    String updateAddress(Address address, Long id);
    void getAllAddressesWithAgencies();
    Map<String, List<Agency>> getAgenciesGroupedByRegion();


}
