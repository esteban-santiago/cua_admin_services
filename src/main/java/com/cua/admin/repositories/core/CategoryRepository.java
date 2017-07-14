package com.cua.admin.repositories.core;

import com.cua.admin.model.core.profiles.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByDescription(String description);

    //Category findOne(Integer id);
}
