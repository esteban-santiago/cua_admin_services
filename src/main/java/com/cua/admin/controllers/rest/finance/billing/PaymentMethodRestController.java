package com.cua.admin.controllers.rest.finance.billing;

import com.cua.admin.model.finance.billing.PaymentMethod;
import com.cua.admin.services.finance.billing.PaymentMethodService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sapi/finance/billing")
public class PaymentMethodRestController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @RequestMapping(value = "/payment", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<PaymentMethod>> get() {
        return new ResponseEntity<>(paymentMethodService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/payment/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PaymentMethod> get(@PathVariable("id") Integer id) throws Throwable {
        return new ResponseEntity<>(paymentMethodService.get(id), HttpStatus.OK);
    }
}
