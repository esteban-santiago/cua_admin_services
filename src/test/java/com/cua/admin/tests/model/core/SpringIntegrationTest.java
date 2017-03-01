package com.cua.admin.tests.model.core;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public abstract class SpringIntegrationTest {

}
