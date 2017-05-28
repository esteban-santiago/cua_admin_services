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
import java.util.ArrayList;
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

    //private Long friId_1, friId_2, rciId_1;

    private FlightRecordIssued fri1, fri2;
    private ReceiptIssued rci;

    @Before
    public void setUp() throws Throwable {
        Payment account = new Payment();
        account.setCurrency(Currency.ARS);

        fri1 = new FlightRecordIssued();
        account.setAmount(3544F);
        fri1.getPayments().add(account);
        fri1.setPerson(personService.getMember(100));
        financeService.save(fri1);

        assertThat(fri1.getId()).isGreaterThan(0);
        assertThat(fri1.getLegalId()).isGreaterThanOrEqualTo(70000000);

        fri2 = new FlightRecordIssued();
        account.setAmount(2544F);
        fri2.getPayments().add(account);
        fri2.setPerson(personService.getMember(100));
        financeService.save(fri2);

        assertThat(fri2.getId()).isGreaterThan(0);
        assertThat(fri2.getLegalId()).isGreaterThanOrEqualTo(70000000);

        //friId_1 = fri.getId();

        FlightRecordIssued fri3 = new FlightRecordIssued();
        account.setAmount(1544F);
        fri3.getPayments().add(account);
        fri3.setPerson(personService.getMember(100));
        financeService.save(fri3);

        assertThat(fri3.getId()).isGreaterThan(0);
        assertThat(fri3.getLegalId()).isGreaterThanOrEqualTo(70000000);

        //friId_2 = fri2.getId();

        rci = new ReceiptIssued();
        Payment credit = new Payment();
        
        rci.setCompensatedDocuments(new ArrayList());
        rci.getCompensatedDocuments().add(fri1);
        rci.getCompensatedDocuments().add(fri2);
        rci.getCompensatedDocuments().add(fri3);
        
        credit.setMethod(paymentMethodService.get(4)); //Tarjeta de Crédito
        credit.setTerm(paymentTermRepository.findById(3)); //Pago en una cuota
        credit.setCurrency(Currency.ARS);
        
        credit.setAmount(7632F);
        credit.setCharge(credit.getAmount() * credit.getTerm().getCharge());

        rci.getPayments().add(credit);

        rci.setPerson(personService.getMember(100));

        financeService.save(rci);

        assertThat(rci.getId()).isGreaterThan(0);
        assertThat(rci.getLegalId()).isGreaterThanOrEqualTo(10000000);

        //rciId_1 = rci.getId();

        //System.out.println("RCI: " + rci);
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

        //Una de las magias del cabezón
        //Lo toma del arcivo receipt.json
        byte[] json = IOUtils.toByteArray(receiptJson.getInputStream());
        
        //Graba el recibo
        MvcResult resultPost = mockMvc.perform(
                post("/sapi/finance/document/")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("documentType").value("RCI"))
                .andExpect(jsonPath("compensatedDocuments", hasSize(1)))
                .andExpect(jsonPath("status").value("COMPENSATED"))
                .andReturn();

        Long rciId = Long.parseLong(resultPost.getResponse().getHeader("id"));

        MvcResult resultGet = mockMvc.perform(
                get("/sapi/finance/document/{id}", rciId)
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
