package com.cua.admin.model.finance;

import com.cua.admin.model.finance.billing.PaymentTerm;
import com.cua.admin.model.finance.billing.PaymentMethod;
import com.cua.admin.model.core.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.ToString;

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
    
    @Enumerated(EnumType.STRING)
    private DocumentType documentType; //Tipo de Documento

    private Float amount; //Importe en moneda del documento

    @Enumerated(EnumType.STRING)
    private Currency currency; //Moneda del documento

    private LocalDate expirationDate = LocalDate.now().plusDays(30); //Fecha de vencimiento

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="compensation_document_id", foreignKey = @ForeignKey(name = "document_id_fk"))
    private Document compensationDocument; //(*) Documento de compensación
    
    private LocalDate compensationDate; //(*) Fecha de compensación

    @OneToOne
    @JoinColumn(name="member_id", foreignKey = @ForeignKey(name = "document_member_id_fk"))
    private Member member; //Socio

    @OneToOne
    @JoinColumn(name="payment_method_id", foreignKey = @ForeignKey(name = "document_payment_method_id_fk"))
    private PaymentMethod paymentMethod; //Forma de pago

    @OneToOne
    @JoinColumn(name="payment_term_id", foreignKey = @ForeignKey(name = "document_payment_term_id_fk"))
    private PaymentTerm paymentTerm; //Condiciones de Pago
    
    @OneToOne
    @JoinColumn(name="user_id")
    private User user; //Usuario

    @CreatedDate
    private LocalDate creationDate = LocalDate.now(); //Fecha de documento

    private Long referencedDocumentId; //Documento de referencia
    
    public abstract Long getLegalId();
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.OPENED;
    
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
