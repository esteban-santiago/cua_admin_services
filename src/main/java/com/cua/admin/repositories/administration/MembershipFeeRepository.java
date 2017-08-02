package com.cua.admin.repositories.administration;

import com.cua.admin.model.administration.membership.MembershipFee;
import com.cua.admin.model.core.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipFeeRepository extends JpaRepository<MembershipFee, Long> {
    //Activity findById(Long id);
    List<MembershipFee> findByPersonId(Person person);
    List<MembershipFee> findByMonthAndYear(Integer month, Integer year);
}
