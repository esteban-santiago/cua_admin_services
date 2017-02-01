package com.cua.admin.model.operation.flight;

import com.cua.admin.model.core.Member;
import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author esantiago
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "crew_member") //, uniqueConstraints = {@UniqueConstraint(columnNames = {"member_id"})})
@SuppressWarnings("ValidAttributes")
public class CrewMember implements Serializable {
        @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "crew_member_id_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;
        
    //@NonNull    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "crew_member_member_id_fk"))
    private Member member;
    
    @NonNull
    @Enumerated(EnumType.STRING)
    private CrewMemberRole crewMemberRole;
    
}
