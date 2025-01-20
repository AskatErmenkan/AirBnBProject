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

    private Long id;
    private HouseType houseType;
    private BigDecimal price;
    private Double rating;
    private String description;
    private int room;
    private Boolean furniture;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Address address;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Owner owner;

    @OneToOne(mappedBy = "house")
    private RentInfo rentInfo;

    public House(HouseType houseType, BigDecimal price, Double rating, String description, int room, Boolean furniture) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
    }
}
