package com.cua.admin.model.accounting.documents;

import com.cua.admin.model.accounting.Currency;
import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.User;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "accounting_item")
@DiscriminatorColumn(name = "document_type_discriminator")
public abstract class Document implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "accounting_item_id_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Long id; //Número de doc

    //protected Long legalId;
    
    @Enumerated(EnumType.STRING)
    private DocumentType documentType; //Tipo de Documento

    private LocalDate accountabilityDate = LocalDate.now(); //Fecha de contabilización

    private Float amount; //Importe en moneda del documento

    @OneToOne
    @JoinColumn(name="currency_id")
    private Currency currency; //Moneda del documento

    private Float accountabilityAmount; //Importe en moneda Contable

    @OneToOne
    @JoinColumn(name="accountability_currency_id")
    private Currency accountabilityCurrency; //Moneda contable

    private LocalDate expirationDate = LocalDate.now().plusDays(30); //Fecha de vencimiento

    @OneToOne
    @JoinColumn(name="compensation_document_id")
    private Document compensationDocument; //(*) Documento de compensación

    private LocalDate compensationDate; //(*) Fecha de compensación

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member; //Socio

    //Número de documento contable
    //Indicador Debe/Haber
    @OneToOne
    @JoinColumn(name="user_id")
    private User user; //Usuario

    @CreatedDate
    private LocalDate creationDate = LocalDate.now(); //Fecha de documento

}
