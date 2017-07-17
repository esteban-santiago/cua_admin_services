package com.cua.admin.model.core.building;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "position_in_hangar")
public class PositionInHangar extends Location implements Serializable {
    
    @OneToOne
    @JoinColumn(name = "hangar_id", nullable = false, foreignKey = @ForeignKey(name = "position_in_hangar_hangar_id_fk"))
    private Hangar hangar;
    
    @OneToOne
    @JoinColumn(name = "position_id", nullable = false, foreignKey = @ForeignKey(name = "position_in_hangar_position_id_fk"))
    private Position position;
}
