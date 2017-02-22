package com.cua.admin.model.core.profiles;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author esteban_santiago
 */
@Data
@Entity
@Table(name = "pilot_medical_certification")
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
    private MedicalCertificationClass medicalCertificationClass = MedicalCertificationClass.NONE;
    
    private LocalDate validityFrom;
    
    private LocalDate validityTo;
    
    private String observations;
    
    public void setClassNone() {
        this.medicalCertificationClass = MedicalCertificationClass.NONE;
    }

    public void setClassI() {
        this.medicalCertificationClass = MedicalCertificationClass.CLASS_I;
    }

    public void setClassII() {
        this.medicalCertificationClass = MedicalCertificationClass.CLASS_II;
    }

    public void setClassIII() {
        this.medicalCertificationClass = MedicalCertificationClass.CLASS_III;
    }
    
    private enum MedicalCertificationClass {
        NONE(),
        CLASS_I(),
        CLASS_II(),
        CLASS_III();
    }

}
