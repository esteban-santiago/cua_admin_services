package com.cua.admin.model.core.building;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
public class Hangar extends Location implements Serializable {
    
    @NonNull
    private String description;
    
    @OneToMany(cascade = CascadeType.ALL)
    List<Position> positions;
}
