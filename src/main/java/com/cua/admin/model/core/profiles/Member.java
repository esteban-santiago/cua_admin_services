package com.cua.admin.model.core.profiles;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
public class Member implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "member_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "member_category_id_fk"))
    private Category category;

   @Enumerated(EnumType.STRING)
    private Member.Status status = Member.Status.ACTIVE;
   
    private enum Status {
        ACTIVE(),
        INACTIVE(),
        DISMISS();    
    }
    
    public void activate() {
        this.status = Member.Status.ACTIVE;
    }
 
    public Boolean isActive() {
        return this.status == Member.Status.ACTIVE;
    }
    
    public void deactivate() {
        this.status = Member.Status.INACTIVE;
    }

    public Boolean isInactive() {
        return this.status == Member.Status.INACTIVE;
    }
   
    public void dismiss() {
        this.status = Member.Status.DISMISS;
    }

    public Boolean isDismiss() {
        return this.status == Member.Status.DISMISS;
    }
    
}