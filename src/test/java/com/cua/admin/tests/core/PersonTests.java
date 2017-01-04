package com.cua.admin.tests.core;

import com.cua.admin.model.core.*;
import com.cua.admin.model.core.repositories.CategoryRepository;
import com.cua.admin.model.core.repositories.MemberRepository;
import com.cua.admin.model.core.repositories.NationalityRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTests extends SpringIntegrationTest {

    @Autowired
    private MemberRepository memberService;

    @Autowired
    private CategoryRepository categoryReposity;

    @Autowired
    private NationalityRepository nationalityRepository;

    @Test
    public void createPerson() {
        Address address = new Address();
        address.setStreet("Av. Berlin 729");
        address.setCity("Longchamps");
        address.setZip("1854");

        Address address2 = new Address();
        address2.setStreet("Pasaje Bélgica 837");
        address2.setCity("Adrogué");
        address2.setZip("1846");

        Member member = new Member();
        member.setName("Persona 1");
        member.setCategory(categoryReposity.findById(1));
        member.addAddress(address);
        member.addAddress(address2);
        member.setNationality(nationalityRepository.findById(1));
        member.setIdentityCard(new IdentityCard(IdentityCardType.DNI, "24036873"));
        memberService.save(member);

        Member member2 = new Member();
        member2.setName("Persona 2");
        member2.setCategory(categoryReposity.findById(1));
        member2.setNationality(nationalityRepository.findById(2));
        member2.setIdentityCard(new IdentityCard(IdentityCardType.PASSPORT, "24036873"));
        memberService.save(member2);

        for (IdentityCardType value: IdentityCardType.values())
            System.out.println("value: " + value.getDescription());

        assertThat(memberService.findAll())
                .extracting("address")
                .isNotNull();
    }
}
