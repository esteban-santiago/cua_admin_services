package com.cua.admin.model.accounting;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author esantiago
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "currency_exchange")
public class CurrencyExchange implements Serializable {
    @GenericGenerator(
            name = "SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "currency_exchange_id_seq"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "SequenceGenerator")
    @Id
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Currency source;
    @Enumerated(EnumType.STRING)
    private Currency target;
    private Float rate;
}
