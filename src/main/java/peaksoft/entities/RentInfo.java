package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    private Date checkOut;
    private Date checkIn;

    @ManyToOne
    private Agency agency;

    @ManyToOne
    private Owner owner;

    @OneToOne
    private House house;

}
