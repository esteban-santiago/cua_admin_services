/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.core;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author esteban_santiago
 */
@Data
@Entity
@Table(name = "member_medical_certification")
public class MedicalCertification implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "medical_certification_id_seq"),
                @Parameter(name = "initial_value", value = "1000"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;
    @Enumerated(EnumType.STRING)
    private MedicalCertificationClass medicalCertificationClass;
    private LocalDate validityFrom;
    private LocalDate validityTo;
    private String observations;

}
