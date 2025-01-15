package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Address {
    @Id
    @GeneratedValue(generator = "address_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "address_gen", sequenceName = "address_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String phoneNumber;

    @OneToOne
    private Agency agency;

    @OneToOne
    private House house;


}
