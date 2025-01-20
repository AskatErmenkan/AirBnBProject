package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Gender;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Owner {
    @Id
    @GeneratedValue(generator = "owner_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "owner_gen", sequenceName = "owner_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private Gender gender;

    @ManyToMany
    private List<Agency> agencies;

    @OneToMany(mappedBy = "owner")
    private List<RentInfo> rentInfo;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<House> houses;

    public Owner(String firstName, String lastName, String email, Date dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
