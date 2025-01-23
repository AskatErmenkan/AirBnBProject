package peaksoft.services;

import peaksoft.entities.Address;
import peaksoft.entities.Agency;

import java.util.List;
import java.util.Map;

public interface AddressService {
    Map<Address, Agency> getAddressById(Long id);
    int getAgencyCountByCity(String city);
    List<Address> getAllAddress();
    void updateAddress(Long id,Address address);
    void getAllAddressesWithAgencies();
    public Map<String, List<Agency>> getAgenciesGroupedByRegion();

}
