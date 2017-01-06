package com.cua.admin.model.core.repositories;

import com.cua.admin.model.core.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Integer> {

    List<Nationality> findByDescription(String description);

    Nationality findById(Integer id);
}
