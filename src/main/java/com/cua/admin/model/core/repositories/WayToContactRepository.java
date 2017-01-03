package com.cua.admin.model.core.repositories;

import com.cua.admin.model.core.ContactWay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WayToContactRepository extends JpaRepository<ContactWay, Integer> {

    List<ContactWay> findByIdentificator(String description);

    ContactWay findById(Integer id);
}
