package peaksoft.services.serviceImpl;

import peaksoft.dao.RentInfoDao;
import peaksoft.dao.daoImpl.RentInfoDaoImpl;
import peaksoft.entities.RentInfo;
import peaksoft.services.RentInfoService;

import java.time.LocalDate;
import java.util.List;

public class RentInfoServiceImpl  implements RentInfoService {
    RentInfoDao rentInfoDao = new RentInfoDaoImpl();

    @Override
    public void saveRentInfo(RentInfo rentInfo) {
    rentInfoDao.saveRentInfo(rentInfo);
    }

    @Override
    public RentInfo getRentInfoById(Long id) {
        return null;
    }

    @Override
    public List<RentInfo> getAllRentInfos() {
        return List.of();
    }

    @Override
    public void updateRentInfo(Long id, RentInfo rentInfo) {

    }

    @Override
    public void deleteRentInfo(Long id) {

    }

    @Override
    public List<RentInfo> getRentInfosBetweenDates(LocalDate startDate, LocalDate endDate) {
        return List.of();
    }

    @Override
    public int countRentedHousesByAgency(Long agencyId, LocalDate date) {
        return 0;
    }
}
