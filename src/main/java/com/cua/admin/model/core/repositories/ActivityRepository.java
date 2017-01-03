package com.cua.admin.model.core.repositories;

import com.cua.admin.model.core.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findByDescription(String description);

    Activity findById(Integer id);
}
