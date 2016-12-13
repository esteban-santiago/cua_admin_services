package com.cua.admin.tests.core;

import com.cua.admin.model.core.Address;
import com.cua.admin.model.core.Category;
import com.cua.admin.model.core.IdentityDocument;
import com.cua.admin.model.core.IdentityDocumentType;
import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.Nationality;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import com.cua.admin.repositories.CategoryRepository;
import com.cua.admin.repositories.MemberRepository;
import com.cua.admin.repositories.NationalityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PersonTests {

    @Autowired
    private MemberRepository memberService;
    @Autowired
    private CategoryRepository categoryReposity;
    @Autowired
    private NationalityRepository nationalityRepository;

    @Test
    public void createNationality() {
        Nationality argentinean = new Nationality("Argentina");
        Nationality brazilian = new Nationality("Brasilera");
        nationalityRepository.save(argentinean);
        nationalityRepository.save(brazilian);
    }

    @Test
    public void createCategory() {
        Category category = new Category("Socio");
        categoryReposity.save(category);
        Category category2 = new Category("Empleado");
        categoryReposity.save(category2);
    }

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

        Category category = categoryReposity.findByDescription("Socio").get(0);
        Nationality argentinean = nationalityRepository.findByDescription("Argentina").get(0);
        Assert.notNull(argentinean);
        Nationality brazilian = nationalityRepository.findByDescription("Brasilera").get(0);
        Assert.notNull(brazilian);

        Member member = new Member();
        member.setName("Socio 1");
        member.setCategory(category);
        member.addAddress(address);
        member.addAddress(address2);
        member.setNationality(argentinean);
        memberService.save(member);

        Member member2 = new Member();
        member2.setName("Socio 2");
        member2.setCategory(category);
        member2.setNationality(brazilian);
        member2.setIdentityDocument(new IdentityDocument(IdentityDocumentType.DNI, "24036873"));
        memberService.save(member2);

        memberService.findAll().stream().map((p) -> {
            Assert.notNull(p.getAddress());
            return p;
        }).forEach((p) -> {
            System.out.println(p.toString());
        });
    }
    
    /*@Test
    public void testIdentityDocument() {
        IdentityDocument dni = IdentityDocument.DNI;
        dni.setNumber("24036873");
        System.out.println("DNI.name(): " + dni.name());
        System.out.println("DNI.number(): " + dni.getNumber());
        System.out.println("DNI.ordinal(): " + dni.ordinal());
        for (IdentityDocument value : IdentityDocument.values()) {
            System.out.println(value.name());
        }
    }*/
}
