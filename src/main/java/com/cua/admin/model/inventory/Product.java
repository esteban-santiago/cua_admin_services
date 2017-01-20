package com.cua.admin.model.inventory;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author esteban_santiago
 */
@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "product_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Long id;
    
    private String description;
    
    @OneToOne(cascade = CascadeType.ALL)    
    @JoinColumn(name = "group_id", nullable = false, foreignKey = @ForeignKey(name = "product_group_id_fk"))
    private ProductGroup group;

    @OneToOne(cascade = CascadeType.ALL)    
    @JoinColumn(name = "subgroup_id", nullable = false,  foreignKey = @ForeignKey(name = "product_subgroup_id_fk"))
    private ProductSubGroup subGroup;


    @Enumerated(EnumType.STRING)   
    private ProductType type;
}
