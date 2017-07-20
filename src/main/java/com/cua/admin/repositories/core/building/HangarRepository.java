package com.cua.admin.repositories.core.building;

import com.cua.admin.model.core.building.Hangar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HangarRepository extends JpaRepository<Hangar, Integer> {
}
