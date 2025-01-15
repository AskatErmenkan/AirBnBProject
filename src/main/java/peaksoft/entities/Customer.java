package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;

import java.util.Date;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Customer{
    @Id
    @GeneratedValue(generator = "customer_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer_gen", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private Gender gender;
    private String nationality;
    private FamilyStatus familyStatus;


}
