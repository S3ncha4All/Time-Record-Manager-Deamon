package de.adesso.trmdeamon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bucket")
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "bucket")
    private List<Booking> bookings;

    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Bucket parentBucket;


    @OneToMany(mappedBy="parentBucket")
    private List<Bucket> children;

    @ManyToOne
    @JoinColumn(
            name = "time_sheet_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_bucket_time_sheet_id",
                    foreignKeyDefinition = "FOREIGN KEY (time_sheet_id)  REFERENCES time_sheet(id)"
            )
    )
    private TimeSheet timeSheet;

}
