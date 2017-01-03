package com.cua.admin.model.core.repositories;

import com.cua.admin.model.core.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByDescription(String description);

    Category findById(Integer id);
}
