package com.cua.admin.model.core.building;

import com.cua.admin.model.core.*;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Entity
@Table(name = "hangar")
@NoArgsConstructor
@RequiredArgsConstructor
public class Hangar implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "hangar_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    @NonNull
    private String identificator;

    @NonNull
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hangar_id", nullable = false, foreignKey = @ForeignKey(name = "hangar_position_id_fk"))
    private Set<Position> position;    
}
