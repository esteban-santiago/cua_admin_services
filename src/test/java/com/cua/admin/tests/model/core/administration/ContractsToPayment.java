package com.cua.admin.tests.model.core.administration;

import com.cua.admin.services.administration.ContractService;
import com.cua.admin.services.administration.building.LocationService;
import com.cua.admin.services.core.PersonService;
import com.cua.admin.tests.model.core.*;
import com.samskivert.mustache.Mustache.Compiler;
import com.samskivert.mustache.Mustache.TemplateLoader;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ContractsToPayment extends SpringIntegrationTest {
    @Autowired
    private ContractService contractService;

    @Autowired
    private PersonService personService;
    
    @Autowired
    private LocationService locationService;

    @Autowired
    private MockMvc mockMvc;
  
    @Autowired
    private Compiler mustacheCompiler;

    @Autowired
    private TemplateLoader templateLoader;
    
    @Before
    public void createContracts() {

    }
    
    
    @Test
    public void paidWithCash() throws Throwable {
        Map<Object, Object> context = new HashMap<>();
        //context.put("compensatedDocumentId_1", friId_1);
        //context.put("compensatedDocumentId_2", friId_2);
        String json = mustacheCompiler
                .compile(templateLoader.getTemplate("contracts/contractSTC"))
                .execute(context);

        MvcResult resultPost = mockMvc.perform(
                post("/sapi/administration/contract")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("documentType").value("RCI"))
                //.andExpect(jsonPath("compensatedDocuments", hasSize(2)))
                //.andExpect(jsonPath("status").value("COMPENSATED"))
                .andReturn();
    }
    
    //@Test
    public void paidWithCreditCard() {
        
    }
    
    @After
    public void list() {
    }
}
