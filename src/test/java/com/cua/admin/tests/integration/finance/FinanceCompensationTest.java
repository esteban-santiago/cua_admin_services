package com.cua.admin.tests.integration.finance;

import com.cua.admin.model.finance.Currency;
import com.cua.admin.model.finance.billing.Payment;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.repositories.finance.billing.PaymentTermRepository;
import com.cua.admin.services.core.PersonService;
import com.cua.admin.services.finance.FinanceService;
import com.cua.admin.services.finance.billing.PaymentMethodService;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private PersonService personService;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private PaymentTermRepository paymentTermRepository;

    @Value("receipt.json")
    private ClassPathResource receiptJson;

    private Long friId_1, friId_2, rciId_1;

    private FlightRecordIssued fve, fri;
    private ReceiptIssued rci;

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

        byte[] json = IOUtils.toByteArray(receiptJson.getInputStream());

        MvcResult resultPost = mockMvc.perform(
                post("/sapi/finance/document/compensate")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("documentType").value("RCI"))
                .andExpect(jsonPath("compensatedDocuments", hasSize(1)))
                .andReturn();

        Long rciId = Long.parseLong(resultPost.getResponse().getHeader("id"));

        MvcResult resultGet = mockMvc.perform(
                get("/sapi/finance/document/?id={id}", rciId)
                        .with(httpBasic("user", "password"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("documentType").value("RCI"))
                .andExpect(jsonPath("compensatedDocuments", hasSize(1)))
                .andReturn();

        System.out.println("Resultado del get : \n"
                + resultGet.getResponse().getContentAsString());

    }

    //@Test
    public void getMemberNotFound() throws Exception {
        mockMvc.perform(get("/sapi/core/person/{id}", 200000).with(httpBasic("user", "password")))
                .andExpect(status().isNotFound());
    }

}
