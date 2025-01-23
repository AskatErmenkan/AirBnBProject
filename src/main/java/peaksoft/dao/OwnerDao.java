package peaksoft.dao;

import peaksoft.entities.House;
import peaksoft.entities.Owner;

import java.util.List;

public interface OwnerDao {
    void saveOwner(Owner owner);

    void saveOwnerWithHouse(Owner owner, House house);

    Owner getOwnerById(Long id);

    List<Owner> getAllOwners();

    void updateOwner(Long id, Owner owner);

    void deleteOwner(Long id);

    void assignToAgency(Long ownerId, Long agencyId);

    List<String> listOwnersWithAges();

    List<Owner> getOwnersByAgencyId(Long agencyId);
}
