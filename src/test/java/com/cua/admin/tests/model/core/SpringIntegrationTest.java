package com.cua.admin.tests.model.core;

import org.junit.runner.RunWith;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class SpringIntegrationTest {

}