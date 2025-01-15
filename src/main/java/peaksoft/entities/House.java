package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.HouseType;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "houses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class House {
    @Id
    @GeneratedValue(generator = "house_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "house_gen", sequenceName = "house_seq", allocationSize = 1)

    private HouseType houseType;
    private BigDecimal price;
    private Double rating;
    private String description;
    private int room;
    private Boolean furniture;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private RentInfo rentInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Owner owner;




}
