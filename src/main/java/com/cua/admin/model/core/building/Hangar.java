package com.cua.admin.model.core.building;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "hangar")
public class Hangar extends Location implements Serializable {
    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hangar_id", nullable = false, foreignKey = @ForeignKey(name = "hangar_position_id_fk"))
    private Set<Position> position;    
}
