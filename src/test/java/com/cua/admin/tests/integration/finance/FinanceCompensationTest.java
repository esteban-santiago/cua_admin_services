package com.cua.admin.tests.integration.finance;

import com.cua.admin.model.finance.Currency;
import com.cua.admin.model.finance.billing.Payment;
import com.cua.admin.model.finance.documents.CreditNoteIssued;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.repositories.finance.billing.PaymentTermRepository;
import com.cua.admin.services.core.PersonService;
import com.cua.admin.services.finance.FinanceService;
import com.cua.admin.services.finance.billing.PaymentMethodService;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import org.hamcrest.Matchers;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Este es un ejemplo de un Integration Test muy simple que integra un
 * controller, seguridad y búsqueda de un usuario
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FinanceCompensationTest extends SpringIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PersonService personService;

    @Autowired
    FinanceService financeService;

    @Autowired
    PaymentMethodService paymentMethodService;

    @Autowired
    PaymentTermRepository paymentTermRepository;

    Long friId_1, friId_2, rciId_1;

    FlightRecordIssued fve, fri;
    ReceiptIssued rci;

    @Before
    public void setUp() throws Throwable {
        Payment account = new Payment();
        account.setCurrency(Currency.ARS);

        fve = new FlightRecordIssued();
        account.setAmount(3544F);
        fve.getPayments().add(account);
        fve.setPerson(personService.getMember(100));
        financeService.save(fve);

        assertThat(fve.getId()).isGreaterThan(0);
        assertThat(fve.getLegalId()).isGreaterThanOrEqualTo(70000000);

        fri = new FlightRecordIssued();
        account.setAmount(2544F);
        fri.getPayments().add(account);
        fri.setPerson(personService.getMember(100));
        financeService.save(fri);

        assertThat(fri.getId()).isGreaterThan(0);
        assertThat(fri.getLegalId()).isGreaterThanOrEqualTo(70000000);

        friId_1 = fri.getId();

        FlightRecordIssued fri2 = new FlightRecordIssued();
        account.setAmount(1544F);
        fri2.getPayments().add(account);
        fri2.setPerson(personService.getMember(100));
        financeService.save(fri2);

        assertThat(fri.getId()).isGreaterThan(0);
        assertThat(fri.getLegalId()).isGreaterThanOrEqualTo(70000000);

        friId_2 = fri2.getId();

        rci = new ReceiptIssued();
        Payment credit = new Payment();
        credit.setMethod(paymentMethodService.get(4)); //Tarjeta de Crédito
        credit.setTerm(paymentTermRepository.findById(3)); //Pago en una cuota
        credit.setCurrency(Currency.ARS);

        rci.getPayments().add(credit);

        rci.setPerson(personService.getMember(100));

        financeService.save(rci);

        assertThat(rci.getId()).isGreaterThan(0);
        assertThat(rci.getLegalId()).isGreaterThanOrEqualTo(10000000);

        rciId_1 = rci.getId();

        System.out.println("RCI: " + rci);
    }

    //@After
    public void tearDown() throws Exception {

    }

    //@Test
    public void getMember() throws Exception {
        mockMvc.perform(get("/sapi/core/person/{id}", 100).with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("nationality.description").value("Argentina"));
    }

    @Test
    public void getFlightRecord() throws Exception {
        /*
        mockMvc.perform(get("/sapi/finance/document/?id={id}", rciId_1).with(httpBasic("user", "password"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("documentType").value("RCI"));
        
        mockMvc.perform(
                get("/sapi/finance/document/?id={id}", friId_1).with(httpBasic("user", "password"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("documentType").value("FRI"));
         */
        //System.out.println(asJsonString(rci));

        MvcResult resultPost = mockMvc.perform(
                post("/sapi/finance/document/compensate")
                        .content(getReceipt()).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("documentType").value("RCI"))
                .andExpect(jsonPath("compensatedDocuments", Matchers.hasSize(1)))
                .andReturn();

        Long rciId = Long.parseLong(resultPost.getResponse().getHeader("id"));

        MvcResult resultGet = mockMvc.perform(
                get("/sapi/finance/document/?id={id}", rciId).with(httpBasic("user", "password"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("documentType").value("RCI"))
                .andExpect(jsonPath("compensatedDocuments", Matchers.hasSize(1)))
                .andReturn();

        System.out.println("Resultado del get : \n"
                + resultPost.getResponse().getContentAsString());

    }

    //@Test
    public void getMemberNotFound() throws Exception {
        mockMvc.perform(get("/sapi/core/person/{id}", 200000).with(httpBasic("user", "password")))
                .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
    }

    public static String getReceipt() {
        return "{"
                + "\"documentType\":\"RCI\",\"documentType\":\"RCI\",\"expirationDate\":\"2017-05-20\","
                + "\"compensationDate\":\"2017-05-20\","
                + "\"person\":{\"id\":100,\"name\":\"SANTIAGO, Esteban\",\"dateOfCreation\":\"2017-02-03\",\"dateOfBirth\":\"1974-08-02\",\"nationality\":{\"description\":\"Argentina\",\"id\":1},\"identityCard\":{\"identityCardNumber\":\"24036873\",\"identityCardType\":\"DNI\"},\"addresses\":[],\"contactWays\":[],\"status\":\"ACTIVE\",\"memberProfile\":{\"id\":100,\"category\":{\"description\":\"Socio\",\"id\":1},\"status\":\"ACTIVE\",\"active\":true,\"dismiss\":false},\"pilotProfile\":{\"id\":100,\"licence\":\"24036873\",\"ratings\":[],\"medicalCertifications\":[{\"id\":1,\"medicalCertificationClass\":\"CLASS_I\",\"validityFrom\":\"2017-01-04\",\"validityTo\":\"2020-01-04\",\"observations\":\"Observaciones!!!\"}],\"pilotCertifications\":[]},\"customerProfile\":null,\"employeeProfile\":null,\"active\":true},\"payments\":[{\"method\":{\"id\":5,\"description\":\"Tarjeta de DÃƒÂ©bito\",\"paymentTerms\":[{\"id\":5,\"description\":\"1 Cuota\",\"charge\":0.0,\"discount\":0.0}]},\"term\":{\"id\":5,\"description\":\"1 Cuota\",\"charge\":0.0,\"discount\":0.0},\"currency\":\"ARS\",\"amount\":4632.0,\"charge\":0.0,\"discount\":0.0,\"description\":\"\",\"totalAmount\":4632.0}],\"promotions\":[],\"user\":null,\"creationDate\":\"2017-04-19\",\"referencedDocumentId\":null,\"status\":\"OPENED\",\"compensatedBy\":null,"
                + "\"compensatedDocuments\":["
                + "{\"documentType\":\"FRI\",\"id\":1,\"documentType\":\"FRI\",\"expirationDate\":\"2017-05-23\",\"compensationDate\":null,\"person\":{\"id\":100,\"name\":\"SANTIAGO, Esteban\",\"dateOfCreation\":\"2017-02-03\",\"dateOfBirth\":\"1974-08-02\",\"nationality\":{\"description\":\"Argentina\",\"id\":1},\"identityCard\":{\"identityCardNumber\":\"24036873\",\"identityCardType\":\"DNI\"},\"addresses\":[],\"contactWays\":[],\"status\":\"ACTIVE\",\"memberProfile\":{\"id\":100,\"category\":{\"description\":\"Socio\",\"id\":1},\"status\":\"ACTIVE\",\"active\":true,\"dismiss\":false},\"pilotProfile\":{\"id\":100,\"licence\":\"24036873\",\"ratings\":[],\"medicalCertifications\":[{\"id\":1,\"medicalCertificationClass\":\"CLASS_I\",\"validityFrom\":\"2017-01-04\",\"validityTo\":\"2020-01-04\",\"observations\":\"Observaciones!!!\"}],\"pilotCertifications\":[]},\"customerProfile\":null,\"employeeProfile\":null,\"active\":true},\"payments\":[{\"id\":1,\"method\":null,\"term\":null,\"currency\":\"ARS\",\"amount\":1152.0,\"charge\":0.0,\"discount\":0.0,\"description\":null,\"totalAmount\":1152.0}],\"promotions\":[],\"user\":null,\"creationDate\":\"2017-04-23\",\"referencedDocumentId\":101,\"status\":\"OPENED\",\"compensatedBy\":null,\"compensatedDocuments\":[],\"legalId\":70000000,\"opened\":true,\"discount\":0.0,\"charge\":0.0,\"totalAmount\":1152.0,\"compensated\":false,\"amount\":1152.0,\"canceled\":false}"
                + "],\"legalId\":10000000,\"charge\":-0.0,\"total Amount\":-4632.0,\"amount\":-4632.0,\"opened\":true,\"discount\":0.0,\"compensated\":false,\"canceled\":false}";
    }
}
