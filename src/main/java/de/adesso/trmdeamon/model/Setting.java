package de.adesso.trmdeamon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "settings")
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String value;

    @ManyToOne
    @JoinColumn(
            name = "time_sheet_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_settings_time_sheet_id",
                    foreignKeyDefinition = "FOREIGN KEY (time_sheet_id)  REFERENCES time_sheet(id)"
            )
    )
    private TimeSheet timeSheet;
}
