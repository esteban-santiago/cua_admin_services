package com.cua.admin.model.administration.contract;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
