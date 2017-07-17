package com.cua.admin.model.core.building;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "field")
@NoArgsConstructor
@RequiredArgsConstructor
public class Field extends Location implements Serializable {

    @NonNull
    private Integer sideOne;

    @NonNull
    private Integer sideTwo;

}
