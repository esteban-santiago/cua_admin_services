package com.cua.admin.repositories.core.organization;

import com.cua.admin.model.core.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    //Organization findById(Integer id);
}
