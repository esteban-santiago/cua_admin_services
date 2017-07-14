package com.cua.admin.repositories.core;

import com.cua.admin.model.core.Nationality;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Integer> {
    List<Nationality> findByDescription(String description);

    //Nationality findOne(Integer id);
}
