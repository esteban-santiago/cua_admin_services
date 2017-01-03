package com.cua.admin.model.accounting.items;

import com.cua.admin.model.accounting.Currency;
import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.User;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Partidas abiertas y compensadas
 * @author esteban_santiago
 */
/*
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "accounting_item")
@DiscriminatorColumn(name = "accounting_item_discriminator")
public abstract class AccountingItem implements Serializable {

    @Id
    private Long id; //Número de doc
    private DocumentType documentType; //Tipo de Documento
    private LocalDate creationDate; //Fecha de documento
    private LocalDate accountabilityData; //Fecha de contabilización
    private Float amount; //Importe en moneda del documento	
    private Currency currency; //Moneda del documento
    private Float accountabilityAmount; //Importe en moneda Contable
    private Currency accountabilityCurrency; //Moneda contable
    private LocalDate expirationDate; //Fecha de vencimiento
    @ManyToOne
    @JoinColumn(name="compensation_document_id")
    private AccountingItem compensationDocument; //(*) Documento de compensación
    private LocalDate compensationDate; //(*) Fecha de compensación
    private Member member; //Socio
    //Número de documento contable	
    //Indicador Debe/Haber
    private User _user; //Usuario    
}*/
