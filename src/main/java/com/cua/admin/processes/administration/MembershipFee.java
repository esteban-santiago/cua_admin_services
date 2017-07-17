package com.cua.admin.processes.administration;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author esteban_santiago
 */
@Service
public class MembershipFee {

    @Scheduled(fixedDelay=500000000)
    public void doSomething() {
        System.out.println("MembershipFee Generation Process");
    }  
}
