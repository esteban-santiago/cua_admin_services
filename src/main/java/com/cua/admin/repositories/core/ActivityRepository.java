package com.cua.admin.repositories.core;

import com.cua.admin.model.hr.profiles.Activity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findByDescription(String description);

    Activity findById(Integer id);
}
