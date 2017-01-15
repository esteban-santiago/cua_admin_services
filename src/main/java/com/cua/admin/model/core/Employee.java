package com.cua.admin.model.core;

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
@SequenceGenerator(name="employee_id_seq", sequenceName="employee_id_seq",allocationSize=1)    
public class Employee extends Member {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq" )  
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "activity_id", foreignKey = @ForeignKey(name = "activity_id_fk"))
    private Activity activity;

}
