package com.cua.admin.controllers.rest.finance.documents;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.DocumentType;
import com.cua.admin.model.finance.documents.ReceiptIssued;
import com.cua.admin.model.finance.documents.exceptions.DocumentNotFoundException;
import com.cua.admin.services.finance.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(documentService.getAllByType(DocumentType.FRI), HttpStatus.OK);
    }

    @RequestMapping(value = "/", params = {"person_id"}, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<? extends Document>> getByPerson(@RequestParam(value = "person_id") Integer id) {
        return new ResponseEntity<>(documentService.getAllByPerson(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<? extends Document>> getAll() {
        return new ResponseEntity<>(documentService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", params = {"referenced_document_id"}, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<? extends Document> getByReferencedDocument(@RequestParam(value = "referenced_document_id") Integer id) {
        try {
            return ResponseEntity.ok(documentService.getByReferencedDocumentId(id));
        } catch (DocumentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/receipt", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<? extends Document> compensate(ReceiptIssued receipt) {
        documentService.save(receipt);
        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }
    
/*
    @RequestMapping(value = "/document/", params = {"referenced_document_id"}, method = RequestMethod.GET, produces = "application/json")
    public <T extends Document> ResponseEntity<T> getByReferencedDocument(@RequestParam(value = "referenced_document_id") Integer id) throws Throwable {
        System.out.println("getByReferencedDocument()");
        return new ResponseEntity<>(documentService.getByReferencedDocumentId(id), HttpStatus.OK);
    }
*/


}
