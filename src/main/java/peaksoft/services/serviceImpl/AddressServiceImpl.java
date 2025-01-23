package peaksoft.services.serviceImpl;

import peaksoft.dao.AddressDao;
import peaksoft.dao.daoImpl.AddressDaoImpl;
import peaksoft.entities.Address;
import peaksoft.entities.Agency;
import peaksoft.services.AddressService;

import java.util.List;
import java.util.Map;

public class AddressServiceImpl implements AddressService {
    AddressDao addressDao = new AddressDaoImpl();
    @Override
    public Map<Address, Agency> getAddressById(Long id) {
        return addressDao.getAddressById(id);
    }

    @Override
    public int getAgencyCountByCity(String city) {
        return addressDao.getAgencyCountByCity(city);
    }

    @Override
    public List<Address> getAllAddress() {
        return null; //List.of();
    }

    @Override
    public void updateAddress(Long id, Address address) {
        System.out.println(addressDao.updateAddress(address, id));
    }

    @Override
    public void getAllAddressesWithAgencies() {
        addressDao.getAllAddressesWithAgencies();
    }
    @Override
    public Map<String, List<Agency>> getAgenciesGroupedByRegion() {
        return addressDao.getAgenciesGroupedByRegion();

    }

}
