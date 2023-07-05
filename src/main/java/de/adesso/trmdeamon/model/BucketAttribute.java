package de.adesso.trmdeamon.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bucket_attributes")
public class BucketAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "bucket_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_attributes_buckets_id",
                    foreignKeyDefinition = "FOREIGN KEY (booking_id)  REFERENCES buckets(id)"
            )
    )
    private Bucket bucket;
}
