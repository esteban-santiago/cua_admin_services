package com.cua.admin.tests.model.core;

import com.cua.admin.model.core.Address;
import com.cua.admin.model.core.ContactWay;
import com.cua.admin.model.core.IdentityCard;
import com.cua.admin.model.core.IdentityCardType;
import com.cua.admin.model.core.Person;
import com.cua.admin.model.core.profiles.Member;
import com.cua.admin.model.core.profiles.Pilot;
import com.cua.admin.model.core.profiles.PilotRating;
import com.cua.admin.repositories.core.CategoryRepository;
import com.cua.admin.repositories.core.NationalityRepository;
import com.cua.admin.repositories.core.PersonRepository;
import com.cua.admin.repositories.operation.flight.FlightRecordRepository;
import com.cua.admin.services.core.PersonService;
import java.util.HashSet;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MemberTests extends SpringIntegrationTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;
    
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

        Person partner = new Person();

        partner.setName("Socio 1");
        partner.setAddresses(new HashSet<>());
        partner.addAddress(address);
        partner.addAddress(address2);
        partner.setContactWays(new HashSet<>());
        partner.addContactWay(new ContactWay("M", "esteban.s@gmail.com"));
        partner.addContactWay(new ContactWay("M", "esteban.san@gmail.com"));

        partner.setIdentityCard(new IdentityCard(IdentityCardType.DNI, "24036873"));
        partner.setNationality(nationalityRepository.findById(1).get());
        //Es socio
        partner.setMemberProfile(new Member());
        partner.getMemberProfile().setCategory(categoryReposity.findById(1).get());
        //Es piloto
        partner.setPilotProfile(new Pilot());
        partner.getPilotProfile().setRatings(new HashSet<>());
        partner.getPilotProfile().addRating(PilotRating.IFR);
        partner.getPilotProfile().addRating(PilotRating.CROSSING);
  
        personService.save(partner);

        /*
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
        */
       
        personService.getAll()
                .stream().forEach(
                        person -> System.out.println("Persona: " + person)
                );
        
        
        flightRecordRepository.findAll()
                .stream().forEach(
                        aRecord -> System.out.println(aRecord)
                );
    }   
}