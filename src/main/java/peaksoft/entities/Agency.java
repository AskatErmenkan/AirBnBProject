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

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany
    private List<Owner> owner;

    @OneToMany
    private List<RentInfo> rentInfo;



}
