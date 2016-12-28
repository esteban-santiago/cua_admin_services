package com.cua.admin.model.accounting;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "currency")
public class Currency implements Serializable {

    @Id
    private Integer id;

    private String description;

}
