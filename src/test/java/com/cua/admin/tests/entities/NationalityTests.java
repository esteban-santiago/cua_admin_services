package com.cua.admin.tests.entities;

import com.cua.admin.model.core.Nationality;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.cua.admin.repositories.NationalityRepository;
import org.junit.Before;

@RunWith(SpringRunner.class)
@SpringBootTest

public class NationalityTests {

    @Autowired
    private NationalityRepository nationalityRepository;

    @Before
    @Test
    public void createNationality() {
        Nationality peruvian = new Nationality("Peruana");
        Nationality argentinean = new Nationality("Argentina");
        Nationality brazilian = new Nationality("Brasilera");
        nationalityRepository.save(peruvian);
        nationalityRepository.save(argentinean);
        nationalityRepository.save(brazilian);
    }

    @Test
    public void createEmployee() {
        Nationality peruvian = nationalityRepository.findByDescription("Peruana").get(0);

    }

    @Test
    public void getEmployeesByNationality() {
        Nationality peruvian = nationalityRepository.findById(2);
    }
}
