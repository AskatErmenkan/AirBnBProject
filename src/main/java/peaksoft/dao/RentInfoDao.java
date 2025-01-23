package peaksoft.dao;

import peaksoft.entities.RentInfo;

import java.time.LocalDate;
import java.util.List;

public interface RentInfoDao {
    void saveRentInfo(RentInfo rentInfo);

    RentInfo getRentInfoById(Long id);

    List<RentInfo> getAllRentInfos();

    void updateRentInfo(Long id, RentInfo rentInfo);

    void deleteRentInfo(Long id);

    List<RentInfo> getRentInfosBetweenDates(LocalDate startDate, LocalDate endDate);

    int countRentedHousesByAgency(Long agencyId, LocalDate date);
}
