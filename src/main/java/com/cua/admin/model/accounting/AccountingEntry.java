/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting;

import com.cua.admin.model.accounting.documents.DocumentType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author esteban_santiago
 */
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "accounting_entry")
public class AccountingEntry implements Serializable {

    public AccountingEntry() {
    }
    @Id
    private Integer id;
    private LocalDate creationDate;
    private LocalTime creationTime;
    private String debit;
    private String credit;
    private DocumentType documentType;
    

/*    
Año fiscal
Periodo Fiscal
Descripción Asiento
Debe
Haber
Tipo de documento
Número de documento
Moneda del documento
Importe en moneda del documento
Moneda local
Importe en moneda local
Cuenta contable
*/
}
