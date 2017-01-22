/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.processes.accounting;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author esteban_santiago
 */
@Service
public class Accounting {

    @Scheduled(fixedDelay=500000000)
    public void doSomething() {
        System.out.println("Hola");
    }  
}
