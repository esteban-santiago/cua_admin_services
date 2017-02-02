package com.cua.admin.repositories.core;

import com.cua.admin.model.core.profiles.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByDescription(String description);

    Category findById(Integer id);
}
