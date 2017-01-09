/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.accounting.entries;

import com.cua.admin.model.accounting.documents.DocumentType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author esteban_santiago
 */
@Data
@Table(name="template_entry")
@Entity
public class TemplateEntry implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "template_entry_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id; //Número de asiento modelo
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "template_entry_line_id", foreignKey = @ForeignKey(name = "template_entry_line_id_fk"))
    private Set <TemplateEntryLine>entryLines = new HashSet<>(); 
    public void addEntryLine(TemplateEntryLine entryLine) {
        this.entryLines.add(entryLine);
    }

}
