/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories;

import com.cua.admin.model.entities.Member;
import com.cua.admin.model.entities.Nationality;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author esteban_santiago
 */
public interface MemberRepository extends CrudRepository<Member, Integer> {

    List<Member> findByName(String name);
    List<Member> findByNationality(Nationality nationality);
    Member findById(Integer id);
}
