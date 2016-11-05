/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories;

import com.cua.admin.model.entities.Nationality;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author esteban_santiago
 */
public interface NationalityRepository extends JpaRepository<Nationality, Integer> {

    List<Nationality> findByDescription(String description);

    Nationality findById(Integer id);
}
