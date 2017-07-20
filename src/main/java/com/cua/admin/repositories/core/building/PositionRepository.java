package com.cua.admin.repositories.core.building;

import com.cua.admin.model.core.building.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    //List<Position> findByHangar(Hangar hangar);
}
