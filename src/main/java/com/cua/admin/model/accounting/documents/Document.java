/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting.documents;

import com.cua.admin.model.accounting.Currency;
import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.User;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author esteban_santiago
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
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
    @Enumerated(EnumType.STRING)
    private DocumentType documentType; //Tipo de Documento
    private LocalDate creationDate; //Fecha de documento
    private LocalDate accountabilityDate; //Fecha de contabilización
    private Float amount; //Importe en moneda del documento	
    @OneToOne
    @JoinColumn(name="currency_id")
    private Currency currency; //Moneda del documento
    private Float accountabilityAmount; //Importe en moneda Contable
    @OneToOne
    @JoinColumn(name="accountability_currency_id")
    private Currency accountabilityCurrency; //Moneda contable
    private LocalDate expirationDate; //Fecha de vencimiento
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
    private User _user; //Usuario    

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the documentType
     */
    public DocumentType getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType the documentType to set
     */
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    /**
     * @return the creationDate
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the accountabilityDate
     */
    public LocalDate getAccountabilityDate() {
        return accountabilityDate;
    }

    /**
     * @param accountabilityDate
     */
    public void setAccountabilityDate(LocalDate accountabilityDate) {
        this.accountabilityDate = accountabilityDate;
    }

    /**
     * @return the amount
     */
    public Float getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * @return the currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * @return the accountabilityAmount
     */
    public Float getAccountabilityAmount() {
        return accountabilityAmount;
    }

    /**
     * @param accountabilityAmount the accountabilityAmount to set
     */
    public void setAccountabilityAmount(Float accountabilityAmount) {
        this.accountabilityAmount = accountabilityAmount;
    }

    /**
     * @return the accountabilityCurrency
     */
    public Currency getAccountabilityCurrency() {
        return accountabilityCurrency;
    }

    /**
     * @param accountabilityCurrency the accountabilityCurrency to set
     */
    public void setAccountabilityCurrency(Currency accountabilityCurrency) {
        this.accountabilityCurrency = accountabilityCurrency;
    }

    /**
     * @return the expirationDate
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * @return the compensationDocument
     */
    public Document getCompensationDocument() {
        return compensationDocument;
    }

    /**
     * @param compensationDocument the compensationDocument to set
     */
    public void setCompensationDocument(Document compensationDocument) {
        this.compensationDocument = compensationDocument;
    }

    /**
     * @return the compensationDate
     */
    public LocalDate getCompensationDate() {
        return compensationDate;
    }

    /**
     * @param compensationDate the compensationDate to set
     */
    public void setCompensationDate(LocalDate compensationDate) {
        this.compensationDate = compensationDate;
    }

    /**
     * @return the member
     */
    public Member getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * @return the _user
     */
    public User getUser() {
        return _user;
    }

    /**
     * @param _user the _user to set
     */
    public void setUser(User _user) {
        this._user = _user;
    }

    
}
