/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.model.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author esteban_santiago
 */

@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(schema="nextg", name="member")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

@SuppressWarnings("ValidPrimaryTableName")
public class Member extends Person {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="member_id_seq" )
    @SequenceGenerator(name="member_id_seq", sequenceName="nextg.member_id_seq",allocationSize=1)
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "nationality_id", foreignKey = @ForeignKey(name = "member_nationality_id_fk"))
    private Nationality nationality; //Nacionalidad
    

    
}
