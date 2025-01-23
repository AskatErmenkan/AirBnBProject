package peaksoft.services.serviceImpl;

import peaksoft.dao.HouseDao;
import peaksoft.dao.daoImpl.HouseDaoImpl;
import peaksoft.entities.House;
import peaksoft.services.HouseService;

import java.time.LocalDate;
import java.util.List;

public class HouseServiceImpl implements HouseService {
     HouseDao houseDao = new HouseDaoImpl();

    @Override
    public void saveHouse(House house, Long ownerId) {
    houseDao.saveHouse(house, ownerId);
    }

    @Override
    public House getHouseById(Long id) {
        return null;
    }

    @Override
    public List<House> getAllHouses() {
        return List.of();
    }

    @Override
    public void updateHouse(Long id, House house) {

    }

    @Override
    public void deleteHouse(Long id) {

    }

    @Override
    public List<House> getHousesByRegion(String region) {
        return List.of();
    }

    @Override
    public List<House> getHousesByAgencyId(Long agencyId) {
        return List.of();
    }

    @Override
    public List<House> getHousesByOwnerId(Long ownerId) {
        return List.of();
    }

    @Override
    public List<House> getHousesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return List.of();
    }
}
