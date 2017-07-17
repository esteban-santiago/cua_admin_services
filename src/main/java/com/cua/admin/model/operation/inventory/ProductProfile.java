package com.cua.admin.model.operation.inventory;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "product_profile")
public class ProductProfile implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "product_profile_id_seq"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)    
    @JoinColumn(name = "group_id", nullable = false, foreignKey = @ForeignKey(name = "product_profile_group_id_fk"))
    ProductGroup group;
    
    @OneToOne(cascade = CascadeType.ALL)    
    @JoinColumn(name = "subgroup_id", nullable = false, foreignKey = @ForeignKey(name = "product_profile_subgroup_id_fk"))    
    ProductSubGroup subGroup;

    @OneToOne(cascade = CascadeType.ALL)    
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "product_profile_product_id_fk"))
    Product product;
    
}
