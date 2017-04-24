package com.cua.admin.controllers.rest.finance.documents;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.DocumentType;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.model.finance.documents.exceptions.DocumentNotFoundException;
import com.cua.admin.services.finance.DocumentService;
import com.cua.admin.services.finance.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sapi/finance/document")
public class DocumentRestController {

    @Autowired
    private DocumentService documentService;
    
    @Autowired
    private FinanceService financeService;

    @RequestMapping(value = "/flight_record_issued", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<? extends Document>> getFlightRecordsIssued() {
        return ResponseEntity.ok(documentService.getAllByType(DocumentType.FRI));
    }

    @RequestMapping(value = "/", params = {"person_id"}, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<? extends Document>> getByPerson(@RequestParam(value = "person_id") Integer id) {
        return ResponseEntity.ok(documentService.getAllByPerson(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<? extends Document>> getAll() {
        return ResponseEntity.ok(documentService.getAll());
    }

    @RequestMapping(value = "/", params = {"referenced_document_id"}, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<? extends Document> getByReferencedDocument(@RequestParam(value = "referenced_document_id") Integer id) {
        try {
            return ResponseEntity.ok(documentService.getByReferencedDocumentId(id));
        } catch (DocumentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/", params = {"id"}, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<? extends Document> get(@RequestParam(value = "id") Long id) throws Throwable {
        return ResponseEntity.ok(documentService.get(id));
    }

    @RequestMapping(value = "/receipt_issued", method = RequestMethod.POST, produces = "application/json", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<ReceiptIssued> save(@RequestBody ReceiptIssued receipt) throws Throwable {
        documentService.save(receipt);
        HttpHeaders headers = new HttpHeaders();
        headers.add("id", receipt.getId().toString());
        return new ResponseEntity(receipt, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/compensate", method = RequestMethod.POST, produces = "application/json", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public <T extends Document> ResponseEntity<? extends Document>  compensate(@RequestBody T document) throws Throwable {
        financeService.compensate(document);
        return ResponseEntity.ok(document);
    }
}
