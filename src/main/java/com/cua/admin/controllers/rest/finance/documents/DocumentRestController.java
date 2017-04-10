package com.cua.admin.controllers.rest.finance.documents;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.DocumentType;
import com.cua.admin.services.finance.DocumentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sapi/finance")
public class DocumentRestController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/document/flight_record_issued", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<? extends Document>> getFlightRecordsIssued() {
        System.out.println("getFlightRecordsIssued()");
        return new ResponseEntity<>(documentService.getAllByType(DocumentType.FRI), HttpStatus.OK);
    }

    @RequestMapping(value = "/document", params = {"person_id"}, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<? extends Document>> getByPerson(@RequestParam(value = "person_id") Integer id) {
        System.out.println("getByPerson()");
        return new ResponseEntity<>(documentService.getAllByPerson(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/document", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<? extends Document>> getAll() {
        System.out.println("getAll()");
        return new ResponseEntity<>(documentService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/document/", params = {"_referenced_document_id"}, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Document> __getByReferencedDocument(@RequestParam(value = "_referenced_document_id") Integer id) throws Throwable {
        System.out.println("_getByReferencedDocument()");
        return new ResponseEntity<>((Document)documentService.getByReferencedDocumentId(id), HttpStatus.OK);
    }


    @RequestMapping(value = "/document/", params = {"referenced_document_id"}, method = RequestMethod.GET, produces = "application/json")
    public <T extends Document> ResponseEntity<T> getByReferencedDocument(@RequestParam(value = "referenced_document_id") Integer id) throws Throwable {
        System.out.println("getByReferencedDocument()");
        return new ResponseEntity<>(documentService.getByReferencedDocumentId(id), HttpStatus.OK);
    }



}
