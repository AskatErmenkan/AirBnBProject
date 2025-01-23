package peaksoft.services.serviceImpl;

import peaksoft.dao.AgencyDao;
import peaksoft.dao.daoImpl.AgencyDaoImpl;
import peaksoft.entities.Agency;
import peaksoft.services.AgencyService;

import java.util.List;
import java.util.Map;

public class AgencyServiceImpl implements AgencyService {
    AgencyDao agencyDao = new AgencyDaoImpl();

    @Override
    public void saveAgency(Agency agency) {
        agencyDao.saveAgency(agency);
    }

    @Override
    public Agency getAgencyById(Long id) {
        return agencyDao.getAgencyById(id);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return null; //List.of();
    }

    @Override
    public void deleteAgency(Long id) {

    }

    @Override
    public void updateAgency(Agency agency) {

    }
}
