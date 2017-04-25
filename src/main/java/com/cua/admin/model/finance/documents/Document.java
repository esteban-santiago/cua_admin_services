package com.cua.admin.model.finance.documents;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.finance.billing.Payment;
import com.cua.admin.model.finance.billing.Promotion;
import com.cua.admin.model.it.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString(exclude = "compensatedBy")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@EntityListeners(AuditingEntityListener.class)

//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "documentType")
@JsonSubTypes({
    @Type(name = "FRI", value = FlightRecordIssued.class),
    @Type(name = "RCI", value = ReceiptIssued.class)
})
public abstract class Document implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "accounting_document_id_seq")
                ,
            @Parameter(name = "initial_value", value = "1")
                ,
            @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Long id; //Número de doc

    //private Long legalId;
    public abstract Long getLegalId();

    @Enumerated(EnumType.STRING)
    private DocumentType documentType; //Tipo de Documento

    private LocalDate expirationDate = LocalDate.now().plusDays(30); //Fecha de vencimiento

    private LocalDate compensationDate; //(*) Fecha de compensación

    @OneToOne
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "person_document_id_fk"))
    private Person person; //Socio

    /*
    @OneToOne
    @JoinColumn(name = "payment_method_id", foreignKey = @ForeignKey(name = "payment_method_document_id_fk"))
    private PaymentMethod paymentMethod; //Forma de pago

    @OneToOne
    @JoinColumn(name = "payment_term_id", foreignKey = @ForeignKey(name = "payment_term_document_id_fk"))
    private PaymentTerm paymentTerm; //Condiciones de Pago

     */
    //private Currency currency;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id", nullable = true, foreignKey = @ForeignKey(name = "document_payment_id_fk"))
    List<Payment> payments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id", nullable = true, foreignKey = @ForeignKey(name = "document_promotion_id_fk"))
    List<Promotion> promotions = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user; //Representa al operador que generó el documento

    @CreatedDate
    private LocalDate creationDate = LocalDate.now(); //Fecha de documento

    private Integer referencedDocumentId; //Documento de referencia

    @Enumerated(EnumType.STRING)
    private DocumentStatus status = DocumentStatus.OPENED;

    @ManyToOne
    @JoinColumn(name = "compensated_by", foreignKey = @ForeignKey(name = "document_id_fk"))
    @JsonBackReference
    private Document compensatedBy; //(*) Documento de compensación

    @OneToMany(mappedBy = "compensatedBy")
    @JsonManagedReference
    private Set<Document> compensatedDocuments = new HashSet<>(); //(*) Documento compensados

    public Float getAmount() {
        return (float) payments.stream().mapToDouble(Payment::getAmount).sum();
    }

    public Float getCharge() {
        return (float) payments.stream().mapToDouble(Payment::getCharge).sum();
    }

    public Float getDiscount() {
        return (float) payments.stream().mapToDouble(Payment::getDiscount).sum();
    }

    public Float getTotalAmount() {
        return (float) payments.stream().mapToDouble(Payment::getTotalAmount).sum();
    }

    /*
    public void setCompensatedDocuments(Set compensatedDocuments) {
        this.compensatedDocuments = compensatedDocuments;
        this.compensatedDocuments.stream().forEach(document -> setCompensatedBy(this));
    }*/
    
    public void open() {
        this.status = DocumentStatus.OPENED;
    }

    public void compensate() {
        this.status = DocumentStatus.COMPENSATED;
    }

    public void cancel() {
        this.status = DocumentStatus.CANCELED;
    }

    public Boolean isOpened() {
        return this.status == DocumentStatus.OPENED;
    }

    public Boolean isCompensated() {
        return this.status == DocumentStatus.COMPENSATED;
    }

    public Boolean isCanceled() {
        return this.status == DocumentStatus.CANCELED;
    }
}
