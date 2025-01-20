package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "agencies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Agency {
    @Id
    @GeneratedValue(generator = "agency_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "agency_gen", sequenceName = "agency_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String phoneNumber;


    @OneToOne(mappedBy = "agency", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Address address;

    @ToString.Exclude
    @ManyToMany(mappedBy = "agencies")
    private List<Owner> owner;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<RentInfo> rentInfo;

    public Agency(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Agency{" +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
