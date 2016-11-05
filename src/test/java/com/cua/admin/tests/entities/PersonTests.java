package com.cua.admin.tests.entities;

import com.cua.admin.model.entities.Address;
import com.cua.admin.model.entities.Person;
import com.cua.admin.model.entities.Category;
import com.cua.admin.model.entities.Nationality;
import com.cua.admin.repositories.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import com.cua.admin.repositories.ActivityRepository;
import com.cua.admin.repositories.CategoryRepository;
import com.cua.admin.repositories.NationalityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PersonTests {

    @Autowired
    private PersonRepository personService;
    @Autowired
    private ActivityRepository activityRepository;
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

        Person member = new Person();
        member.setName("Socio 1");
        member.setCategory(category);
        member.addAddress(address);
        member.addAddress(address2);
        member.setNationality(argentinean);
        personService.save(member);

        Person member2 = new Person();
        member2.setName("Socio 2");
        member2.setCategory(category);
        member2.setNationality(brazilian);
        personService.save(member2);

        for (Person p : personService.findAll()) {
            Assert.notNull(p.getAddress());
            System.out.println(p.toString());
        }
    }
}
