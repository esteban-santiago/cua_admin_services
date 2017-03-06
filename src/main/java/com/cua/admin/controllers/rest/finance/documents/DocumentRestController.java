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
@RequestMapping("/sapi/finance/documents")
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

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<? extends Document>> get() {
        return new ResponseEntity<>(documentService.getAll(), HttpStatus.OK);
    }
}
