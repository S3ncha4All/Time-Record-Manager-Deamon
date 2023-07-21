package de.adesso.trmdeamon.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "begin_booking_timestamp")
    private LocalDateTime begin;

    @Column(name = "end_booking_timestamp")
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(
            name = "time_sheet_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_bookings_time_sheet_id",
                    foreignKeyDefinition = "FOREIGN KEY (time_sheet_id) REFERENCES time_sheet(id)"
            )
    )
    private TimeSheet timeSheet;

    @OneToMany(mappedBy = "booking")
    private List<BookingTags> tags;
}
