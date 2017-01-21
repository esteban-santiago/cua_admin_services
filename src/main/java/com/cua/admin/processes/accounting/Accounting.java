/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.processes.accounting;

import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author esteban_santiago
 */
public class Accounting {

    @Scheduled(fixedDelay=5000)
    public void doSomething() {
        System.out.println("Hola");
    }  
}
