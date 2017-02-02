/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.core.profiles;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author esantiago
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "pilot_certification")
public class PilotCertification implements Serializable {
    @GenericGenerator(
        name = "SequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "pilot_certification_id_seq"),
            @Parameter(name = "initial_value", value = "1"),
            @Parameter(name = "increment_size", value = "1")
        }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;
    
    //Fecha de asignación
    private LocalDate dateOfCreation;
    
    //Fecha de Validez desde
    private LocalDate validityFrom;
    
    //Fecha de Validez hasta
    private LocalDate validityTo;

    public enum Type {     
        APPA(), //Alumno
        PPA(),  //Privado
        PCA(),  //Comercial
        AEP(),  //Aeroaplicador
        IV(),   //Instructor
        @Deprecated
        PCA1(), //Comercial de Primera
        TLA();  //Transporte de Linea aerea
    }
}
