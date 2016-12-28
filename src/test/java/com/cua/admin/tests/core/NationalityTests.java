package com.cua.admin.tests.core;

import com.cua.admin.model.core.Nationality;
import com.cua.admin.repositories.NationalityRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NationalityTests extends SpringIntegrationTest {

    @Autowired
    private NationalityRepository nationalityRepository;

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
