/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories.flight;

import com.cua.admin.model.core.flight.Aircraft;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author esteban_santiago
 */
public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {

    List<Aircraft> findByRegistration(String description);

    Aircraft findById(Integer id);
}
