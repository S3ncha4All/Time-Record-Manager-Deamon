package de.adesso.trmdeamon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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
    private List<Attribute> attributes;

    private LocalDateTime begin;

    private LocalDateTime end;
}
