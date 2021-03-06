package com.cua.admin.repositories.core;

import com.cua.admin.model.core.ContactWay;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactWayRepository extends JpaRepository<ContactWay, Integer> {

    List<ContactWay> findByIdentificator(String description);

    //ContactWay findOne(Integer id);
}
