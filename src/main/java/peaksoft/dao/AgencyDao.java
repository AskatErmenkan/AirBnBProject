package peaksoft.dao;

import peaksoft.entities.Agency;

import java.util.List;
import java.util.Map;

public interface AgencyDao {
    void saveAgency(Agency agency);
    Agency getAgencyById(Long id);
    List<Agency> getAllAgencies();
    void deleteAgencyById(Long id);
    void updateAgency(Agency agency, Long id);
}
