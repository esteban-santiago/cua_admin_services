package com.cua.admin.tests.model.core;

import com.cua.admin.model.core.Address;
import com.cua.admin.model.core.IdentityCard;
import com.cua.admin.model.core.IdentityCardType;
import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.MemberRole;
import com.cua.admin.model.hr.Employee;
import com.cua.admin.model.operation.flight.PilotRating;
import com.cua.admin.repositories.core.CategoryRepository;
import com.cua.admin.repositories.core.NationalityRepository;
import com.cua.admin.repositories.hr.EmployeeRepository;
import com.cua.admin.repositories.operation.flight.FlightRecordRepository;
import com.cua.admin.services.core.MemberService;
import java.util.HashSet;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


public class MemberTests extends SpringIntegrationTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private CategoryRepository categoryReposity;

    @Autowired
    private NationalityRepository nationalityRepository;
    
    @Autowired
    private FlightRecordRepository flightRecordRepository;

    @Test
    public void createMember() throws Throwable {
        Address address = new Address();
        address.setStreet("Av. Berlin 729");
        address.setCity("Longchamps");
        address.setZip("1854");

        Address address2 = new Address();
        address2.setStreet("Pasaje Bélgica 837");
        address2.setCity("Adrogué");
        address2.setZip("1846");

        Member member = new Member();
        member.setName("Socio 1");
        member.setCategory(categoryReposity.findById(1));
        member.setAddresses(new HashSet<>());
        member.addAddress(address);
        member.addAddress(address2);
        member.setIdentityCard(new IdentityCard(IdentityCardType.DNI, "24036873"));
        member.setNationality(nationalityRepository.findById(1));
        member.setRatings(new HashSet<>());
        member.addRating(PilotRating.IFR);
        member.addRating(PilotRating.CROSSING);
        member.setRoles(new HashSet<>());
        member.addRole(MemberRole.PILOT);
        memberService.save(member);

        Member member2 = new Member();
        member2.setName("Socio 2");
        member2.setCategory(categoryReposity.findById(1));
        member2.setNationality(nationalityRepository.findById(2));
        member2.setIdentityCard(new IdentityCard(IdentityCardType.DNI, "24036873"));
        memberService.save(member2);
        
        Member member3 = memberService.get(100);
        Employee god = new Employee();
        BeanUtils.copyProperties(member3, god);
        //memberService.delete(memberService.get(member3));
        //System.out.println("Borrado");
        employeeRepository.save(god);
        
       
        memberService.getAll()
                .stream().forEach(
                        aMember -> System.out.println(aMember)
                );
        
        flightRecordRepository.findAll()
                .stream().forEach(
                        aRecord -> System.out.println(aRecord)
                );
        
        
        
    }
    
}
