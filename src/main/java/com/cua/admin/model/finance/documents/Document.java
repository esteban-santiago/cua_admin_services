package com.cua.admin.model.finance.documents;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.finance.Currency;
import com.cua.admin.model.finance.billing.PaymentMethod;
import com.cua.admin.model.finance.billing.PaymentTerm;
import com.cua.admin.model.it.User;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@ToString(exclude = "compensationDocument")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@EntityListeners(AuditingEntityListener.class)
public abstract class Document implements Serializable {

    @GenericGenerator(
        name = "SequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "accounting_document_id_seq"),
            @Parameter(name = "initial_value", value = "1"),
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

    private Float amount; //Importe en moneda del documento

    @Enumerated(EnumType.STRING)
    private Currency currency; //Moneda del documento

    private LocalDate expirationDate = LocalDate.now().plusDays(30); //Fecha de vencimiento

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compensation_document_id", foreignKey = @ForeignKey(name = "document_id_fk"))
    private Document compensationDocument; //(*) Documento de compensación

    private LocalDate compensationDate; //(*) Fecha de compensación

    @OneToOne
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "person_document_id_fk"))
    private Person person; //Socio

    @OneToOne
    @JoinColumn(name = "payment_method_id", foreignKey = @ForeignKey(name = "payment_method_document_id_fk"))
    private PaymentMethod paymentMethod; //Forma de pago

    @OneToOne
    @JoinColumn(name = "payment_term_id", foreignKey = @ForeignKey(name = "payment_term_document_id_fk"))
    private PaymentTerm paymentTerm; //Condiciones de Pago

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user; //Usuario

    @CreatedDate
    private LocalDate creationDate = LocalDate.now(); //Fecha de documento

    private Integer referencedDocumentId; //Documento de referencia

    @Enumerated(EnumType.STRING)
    private Status status = Status.OPENED;

    private List<Item> items;
    
    public Float getTotalAmount() {
        return (float) items.stream().mapToDouble(
                (item) -> item.getQuantity() * item.getPrice())
                .sum();
    }    
    
    
    public void open() {
        this.status = Status.OPENED;
    }

    public void close() {
        this.status = Status.CLOSED;
    }

    public void cancel() {
        this.status = Status.CANCELED;
    }

    public Boolean isOpened() {
        return this.status == Status.OPENED;
    }

    public Boolean isClosed() {
        return this.status == Status.CLOSED;
    }

    public Boolean isCanceled() {
        return this.status == Status.CANCELED;
    }

    private enum Status {
        OPENED,
        CLOSED,
        CANCELED;
    }
}
