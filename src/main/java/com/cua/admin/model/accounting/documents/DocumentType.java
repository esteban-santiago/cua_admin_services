/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting.documents;

import java.io.Serializable;
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
@Table(name = "document_type")
public class DocumentType implements Serializable {
    @Id
    private Integer id;
    private String documentTypeId;
    private String description;
}
