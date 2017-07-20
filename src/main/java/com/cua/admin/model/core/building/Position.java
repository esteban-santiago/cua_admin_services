package com.cua.admin.model.core.building;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "hangar")
@EqualsAndHashCode(callSuper = false)
@Entity
public class Position extends Location implements Serializable {
    
    @JsonIgnore
    @ManyToOne
    //@JoinColumn(name = "hangar_id", nullable = false, foreignKey = @ForeignKey(name = "position_hangar_id_fk"))
    private Hangar hangar;
    
    @OneToOne
    //@JoinColumn(name = "dimension_id", nullable = false, foreignKey = @ForeignKey(name = "position_dimension_id_fk"))
    private Dimension dimension;

}

