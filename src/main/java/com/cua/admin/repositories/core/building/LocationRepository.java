package com.cua.admin.repositories.core.building;

import com.cua.admin.model.core.building.Hangar;
import com.cua.admin.model.core.building.Location;
import com.cua.admin.model.core.building.Position;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository<T extends Location> extends JpaRepository<T, Integer> {
        List<Position> findByHangar(Hangar hangar);
        Optional<Hangar> findHangarById(Integer id);
        Optional<Position> findPositionById(Integer id);
}
