package de.adesso.trmdeamon.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking_attributes")
public class BookingAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "booking_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_attributes_bookings_id",
                    foreignKeyDefinition = "FOREIGN KEY (booking_id)  REFERENCES bookings(id)"
            )
    )
    private Booking booking;
}
