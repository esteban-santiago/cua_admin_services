package com.cua.admin.model.accounting.entries;

import com.cua.admin.model.core.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Cabecera del asiento
 */
@Data
@Entity
@Table(name = "accounting_entry")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class AccountingEntry implements Serializable {

    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "accounting_entry_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    private String description; //Descripción del asiento

    private Integer fiscalYear;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "accounting_document_id", foreignKey = @ForeignKey(name = "accounting_entry_id_fk"))
    private Set<AccountingEntryItem> entryItems = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "accounting_entry_user_id_fk"))
    private User user;

    @CreatedDate
    private LocalDateTime creationDate;

    public void addEntryItem(AccountingEntryItem entryItem) {
        this.entryItems.add(entryItem);
    } 
}
