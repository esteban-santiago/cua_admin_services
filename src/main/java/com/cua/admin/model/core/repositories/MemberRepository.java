package com.cua.admin.model.core.repositories;

import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    List<Member> findByName(String name);

    List<Member> findByNationality(Nationality nationality);

    Member findById(Integer id);
}
