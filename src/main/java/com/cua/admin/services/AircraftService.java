/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.services;

import com.cua.admin.repositories.flight.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author esteban_santiago
 */

@Service
public class AircraftService {
        @Autowired
        AircraftRepository aircraftRepository;
        
} 
