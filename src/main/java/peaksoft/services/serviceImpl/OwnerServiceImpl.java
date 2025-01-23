package peaksoft.services.serviceImpl;

import peaksoft.dao.OwnerDao;
import peaksoft.dao.daoImpl.OwnerDaoImpl;
import peaksoft.entities.House;
import peaksoft.entities.Owner;
import peaksoft.services.OwnerService;

import java.util.List;

public class OwnerServiceImpl  implements OwnerService {
   OwnerDao ownerDao = new OwnerDaoImpl();

    @Override
    public void saveOwner(Owner owner) {
    ownerDao.saveOwner(owner);
    }

    @Override
    public void saveOwnerWithHouse(Owner owner, House house) {

    }

    @Override
    public Owner getOwnerById(Long id) {
        return null;
    }

    @Override
    public List<Owner> getAllOwners() {
        return List.of();
    }

    @Override
    public void updateOwner(Long id, Owner owner) {

    }

    @Override
    public void deleteOwner(Long id) {

    }

    @Override
    public void assignToAgency(Long ownerId, Long agencyId) {

    }

    @Override
    public List<String> listOwnersWithAges() {
        return List.of();
    }

    @Override
    public List<Owner> getOwnersByAgencyId(Long agencyId) {
        return List.of();
    }
}
