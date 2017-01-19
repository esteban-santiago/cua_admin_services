package com.cua.admin.repositories.core;

import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, Integer> {

    List<Member> findByName(String name);

    List<Member> findByNationality(Nationality nationality);

    Optional<Member> findById(Integer id);
}
