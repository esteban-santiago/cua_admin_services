package com.cua.admin.model.administration;

import com.cua.admin.model.core.Person;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author esantiago
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
//@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "short_term_contract")
public class ShortTermContract extends Contract implements Serializable {

}
