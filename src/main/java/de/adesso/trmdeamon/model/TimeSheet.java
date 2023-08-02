package de.adesso.trmdeamon.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "time_sheets")
public class TimeSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "timeSheet")
    private List<Setting> settings;

    @OneToMany(mappedBy = "timeSheet")
    private List<Booking> bookings;

}
