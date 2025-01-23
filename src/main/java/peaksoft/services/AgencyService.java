package peaksoft.services;

import peaksoft.entities.Agency;

import java.util.List;
import java.util.Map;

public interface AgencyService {
    void saveAgency(Agency agency);
    Agency getAgencyById(Long id);
    List<Agency> getAllAgencies();
    void deleteAgency(Long id);
    void updateAgency(Agency agency);


}
