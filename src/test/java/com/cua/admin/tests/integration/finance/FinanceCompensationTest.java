package com.cua.admin.tests.integration.finance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import com.cua.admin.model.finance.Currency;
import com.cua.admin.model.finance.billing.Payment;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.repositories.finance.billing.PaymentTermRepository;
import com.cua.admin.services.core.PersonService;
import com.cua.admin.services.finance.FinanceService;
import com.cua.admin.services.finance.billing.PaymentMethodService;
import com.cua.admin.tests.model.core.SpringIntegrationTest;
import com.samskivert.mustache.Mustache.Compiler;
import com.samskivert.mustache.Mustache.TemplateLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

    @Autowired
    private Compiler mustacheCompiler;

    @Autowired
    private TemplateLoader templateLoader;

    private Long friId_1, friId_2, friId_3, rciId_1;

    private FlightRecordIssued fri1, fri2, fri3;
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

        friId_1 = fri1.getId();

        assertThat(fri1.getId()).isGreaterThan(0);
        assertThat(fri1.getLegalId()).isGreaterThanOrEqualTo(70000000);

        fri2 = new FlightRecordIssued();
        account.setAmount(2544F);
        fri2.getPayments().add(account);
        fri2.setPerson(personService.getMember(100));
        financeService.save(fri2);

        friId_2 = fri2.getId();

        assertThat(fri2.getId()).isGreaterThan(0);
        assertThat(fri2.getLegalId()).isGreaterThanOrEqualTo(70000000);

        fri3 = new FlightRecordIssued();
        account.setAmount(1544F);
        fri3.getPayments().add(account);
        fri3.setPerson(personService.getMember(100));
        financeService.save(fri3);

        friId_3 = fri3.getId();

        assertThat(fri3.getId()).isGreaterThan(0);
        assertThat(fri3.getLegalId()).isGreaterThanOrEqualTo(70000000);

        
        rci = new ReceiptIssued();
        Payment credit = new Payment();

        rci.setCompensatedDocuments(new ArrayList<>());
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

        rciId_1 = rci.getId();

        assertThat(rci.getId()).isGreaterThan(0);
        assertThat(rci.getLegalId()).isGreaterThanOrEqualTo(10000000);
         
        //assertThat(rci.isCompensated()).isTrue();
    }

    
    //Validar si un documento es compensable = true
    @Test
    public void compensationTC_0() throws Exception {
        //Una de las magias del cabezón -> lo toma del archivo receipt.json
        //byte[] json = IOUtils.toByteArray(receiptJson.getInputStream());

        // Otra de las magias del "cabezón" ;)
        Map<Object, Object> context = new HashMap<>();
        context.put("compensatedDocumentId", friId_1);
        String json = mustacheCompiler
                .compile(templateLoader.getTemplate("receipt_tc0"))
                .execute(context);

        mockMvc.perform(
                post("/sapi/finance/document/")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("documentType").value("RCI"))
                .andExpect(jsonPath("status").value("COMPENSATED"))
                .andExpect(jsonPath("compensatedDocuments", hasSize(1)))
                .andExpect(header().string("compensated", "true"))
                .andReturn();


    }

    //Validar si un documento es compensable = false
    @Test
    public void compensationTC_0_1() throws Exception {
        Map<Object, Object> context = new HashMap<>();
        context.put("compensatedDocumentId", friId_1);
        String json = mustacheCompiler
                .compile(templateLoader.getTemplate("receipt_tc0_1"))
                .execute(context);

        mockMvc.perform(
                post("/sapi/finance/document/")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("documentType").value("RCI"))
                .andExpect(jsonPath("status").value("OPENED"))
                .andExpect(jsonPath("compensatedDocuments", hasSize(0)))
                .andExpect(header().string("compensated", "false"))
                .andReturn();

    }

    /*
    ** Valor del Recibo = Valor de la ficha
    ** 
     */
    @Test
    public void compensationTC_1() throws Exception {
        Map<Object, Object> context = new HashMap<>();
        context.put("compensatedDocumentId", friId_1);
        String json = mustacheCompiler
                .compile(templateLoader.getTemplate("receipt_tc1"))
                .execute(context);

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
    }

    /*
    ** Valor del Recibo = Valor de la ficha
    ** metodo de pago con recargo
     */
    @Test
    public void compensationTC_1_1() throws Exception {
        Map<Object, Object> context = new HashMap<>();
        context.put("compensatedDocumentId", friId_1);
        String json = mustacheCompiler
                .compile(templateLoader.getTemplate("receipt_tc1_1"))
                .execute(context);

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
    }
    
    /*
    ** Valor del Recibo = Valor de la suma de las fichas 
    **  compensadas
     */
    @Test
    public void compensationTC_2() throws Exception {
        Map<Object, Object> context = new HashMap<>();
        context.put("compensatedDocumentId_1", friId_1);
        context.put("compensatedDocumentId_2", friId_2);
        String json = mustacheCompiler
                .compile(templateLoader.getTemplate("receipt_tc2"))
                .execute(context);

        MvcResult resultPost = mockMvc.perform(
                post("/sapi/finance/document/")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("documentType").value("RCI"))
                .andExpect(jsonPath("compensatedDocuments", hasSize(2)))
                .andExpect(jsonPath("status").value("COMPENSATED"))
                .andReturn();
    }

    /*
    ** Valor del Recibo = Valor de la suma de los documentos 
    **  compensados
     */
    @Test
    public void compensationTC_3() throws Exception {
        Map<Object, Object> context = new HashMap<>();
        context.put("compensatedDocumentId_1", rciId_1);
        context.put("compensatedDocumentId_2", friId_1);
        String json = mustacheCompiler
                .compile(templateLoader.getTemplate("receipt_tc3"))
                .execute(context);

        MvcResult resultPost = mockMvc.perform(
                post("/sapi/finance/document/")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("documentType").value("RCI"))
                .andExpect(jsonPath("compensatedDocuments", hasSize(2)))
                .andExpect(jsonPath("compensatedDocuments[0].status").value("COMPENSATED"))
                .andExpect(jsonPath("compensatedDocuments[1].status").value("COMPENSATED"))
                .andExpect(jsonPath("status").value("COMPENSATED"))
                .andReturn();
    }
    
    /*
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

        mockMvc.perform(
                get("/sapi/finance/document/{id}", rciId)
                        .with(httpBasic("user", "password"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("documentType").value("RCI"))
                .andExpect(jsonPath("compensatedDocuments", hasSize(1)))
                .andExpect(jsonPath("status").value("COMPENSATED"))
                .andReturn();
    */
    
}
