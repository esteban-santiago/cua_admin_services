/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories;

import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.Nationality;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author esteban_santiago
 */
public interface MemberRepository extends JpaRepository<Member, Integer> {

    List<Member> findByName(String name);

    List<Member> findByNationality(Nationality nationality);

    Member findById(Integer id);
}
