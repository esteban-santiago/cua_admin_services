package com.cua.admin.controllers.rest.finance;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.DocumentType;
import com.cua.admin.services.finance.FinanceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sapi/finance")
public class FinanceRestController {

    @Autowired
    private FinanceService financeService;
    /*
    @RequestMapping(value = "/compensate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<? extends Document> compensate(FlightRecordIssued flightRecordIssued) {
        return new ResponseEntity<>(financeService.compensate(receipt, flightRecordIssued), HttpStatus.OK);
    }
     */
}
