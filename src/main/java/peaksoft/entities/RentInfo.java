package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "rent_infos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RentInfo {
    @Id
    @GeneratedValue(generator = "rent_info_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rent_info_gen", sequenceName = "rent_info_seq", allocationSize = 1)
    private Long id;
    private LocalDate checkOut;
    private LocalDate checkIn;

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private House house;

    public RentInfo(LocalDate checkOut, LocalDate checkIn) {
        this.checkOut = checkOut;
        this.checkIn = checkIn;
    }
}
