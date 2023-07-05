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

    private String name;

    @ManyToOne
    @JoinColumn(
            name = "bucket_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_bookings_bucket_id",
                    foreignKeyDefinition = "FOREIGN KEY (bucket_id)  REFERENCES bucket(id)"
            )
    )
    private Bucket bucket;

    @OneToMany(mappedBy = "booking")
    private List<BookingAttribute> bookingAttributes;

    @Column(name = "begin_booking_timestamp", nullable = false)
    private LocalDateTime begin;

    @Column(name = "end_booking_timestamp")
    private LocalDateTime end;
}
