package peaksoft.dao;

import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;

import java.time.LocalDate;

public interface CustomerDao {
     public void saveCustomer(String firstName, String lastName, String email, LocalDate dateOfBirth,
                                 Gender gender, String nationality, FamilyStatus familyStatus);
    }


