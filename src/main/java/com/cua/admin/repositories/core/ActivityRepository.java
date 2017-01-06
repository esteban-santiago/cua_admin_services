package com.cua.admin.repositories.core;

import com.cua.admin.model.core.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findByDescription(String description);

    Activity findById(Integer id);
}
