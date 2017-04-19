package com.cua.admin.controllers.rest.finance.documents;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.DocumentType;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.model.finance.documents.exceptions.DocumentNotFoundException;
import com.cua.admin.services.finance.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sapi/finance/document")
public class DocumentRestController {

    @Autowired
    private DocumentService documentService;

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

    @RequestMapping(value = "/receipt_issued", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<ReceiptIssued> saveReceipt(ReceiptIssued receipt) {
        System.out.println(receipt.toString());
        documentService.save(receipt);
        return ResponseEntity.ok(receipt);
    }

}
