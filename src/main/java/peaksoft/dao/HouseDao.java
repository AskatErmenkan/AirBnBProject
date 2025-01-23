package peaksoft.dao;

import peaksoft.entities.House;

import java.time.LocalDate;
import java.util.List;

public interface HouseDao {
    void saveHouse(House house, Long ownerId);

    House getHouseById(Long id);

    List<House> getAllHouses();

    void updateHouse(Long id, House house);

    void deleteHouse(Long id);

    List<House> getHousesByRegion(String region);

    List<House> getHousesByAgencyId(Long agencyId);

    List<House> getHousesByOwnerId(Long ownerId);

    List<House> getHousesBetweenDates(LocalDate startDate, LocalDate endDate);
}
