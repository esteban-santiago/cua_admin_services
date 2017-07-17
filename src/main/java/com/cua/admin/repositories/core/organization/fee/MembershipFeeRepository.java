package com.cua.admin.repositories.core.organization.fee;

import com.cua.admin.model.core.organization.fee.MembershipFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipFeeRepository extends JpaRepository<MembershipFee, Long> {
    //Activity findById(Long id);
}
