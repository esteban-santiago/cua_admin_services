package com.cua.admin.services.administration;

import com.cua.admin.model.administration.MembershipFee;
import com.cua.admin.model.core.Person;
import com.cua.admin.repositories.administration.MembershipFeeRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author esantiago
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MembershipFeeService {
    @Autowired
    private final MembershipFeeRepository membershipFeeRepository;

    /*
    ** Contract Basic Services
    */
    public MembershipFee get(Long id) {
        return this.membershipFeeRepository.findById(id).get();
    }
    
    public void save(MembershipFee membershipFee) {
        this.membershipFeeRepository.save(membershipFee);
    }
    
    public List<MembershipFee> getAll() {
        return this.membershipFeeRepository.findAll();
    }
    
    public List<MembershipFee> getByPerson(Person person) {
        return this.membershipFeeRepository.findByPersonId(person);
    }
    
    public List<MembershipFee> getByMonthAndYer(Integer month, Integer year) {
        return this.membershipFeeRepository.findByMonthAndYear(month, year);
    }
    
}
