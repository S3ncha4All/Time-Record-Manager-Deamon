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
@Table(name = "buckets")
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "bucket")
    private List<Booking> bookings;

    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "parent_id",
            foreignKey = @ForeignKey(
                    name = "fk_bucket_id"
            )
    )
    private Bucket parentBucket;

    @OneToMany(mappedBy="parentBucket")
    private List<Bucket> children;

    @ManyToOne
    @JoinColumn(
            name = "time_sheet_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_bucket_time_sheets_id",
                    foreignKeyDefinition = "FOREIGN KEY (time_sheet_id)  REFERENCES time_sheet(id)"
            )
    )
    private TimeSheet timeSheet;

}
