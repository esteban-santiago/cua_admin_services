package com.cua.admin.model.hr;

import com.cua.admin.model.core.Activity;
import com.cua.admin.model.core.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee")
public class Employee extends Member {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq" )  
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", foreignKey = @ForeignKey(name = "activity_id_fk"))
    private Activity activity;

}
