package com.cua.admin.repositories;

import com.cua.admin.model.core.WayToContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WayToContactRepository extends JpaRepository<WayToContact, Integer> {

    List<WayToContact> findByIdentificator(String description);

    WayToContact findById(Integer id);
}
