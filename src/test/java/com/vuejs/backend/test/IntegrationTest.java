package com.vuejs.backend.test;

import com.vuejs.backend.config.TestProfile;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@AutoConfigureMockMvc
@Transactional
@ActiveProfiles(TestProfile.TEST)
@Ignore
public class IntegrationTest {



}
