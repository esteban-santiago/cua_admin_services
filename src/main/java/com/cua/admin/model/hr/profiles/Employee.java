package com.cua.admin.model.hr.profiles;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "employee_profile")
public class Employee implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "employee_profile_id_seq"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq" )  
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", foreignKey = @ForeignKey(name = "employee_activity_id_fk"))
    private Activity activity;

}
