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
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<? extends Document> get(@PathVariable("id") Long id) throws Throwable {
        return ResponseEntity.ok(documentService.get(id));
    }

    @RequestMapping(value = "/", params = {"person_id"}, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<? extends Document>> getByPerson(@RequestParam(value = "person_id") Integer id) {
        return ResponseEntity.ok(documentService.getAllByPerson(id));
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
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

    @RequestMapping(value = "/is_compensated", params = {"referenced_document_id"}, method = RequestMethod.GET,produces = "text/html")
    public ResponseEntity<String> isCompensated(@RequestParam(value = "referenced_document_id") Integer id) {
        Boolean isCompensated;
        try {
            isCompensated = documentService.getByReferencedDocumentId(id).isCompensated();
        } catch (DocumentNotFoundException e) {
            isCompensated = false;
        }
        return ok()
                .header("Access-Control-Expose-Headers", "compensated")
                .header("compensated", isCompensated.toString())
                .body("");

    }

    @RequestMapping(value = "/is_compensable", method = RequestMethod.POST, produces = "application/json", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public <T extends Document> ResponseEntity<String> isCompensable(@RequestBody T document) throws Throwable {
        return ok()
                .header("Access-Control-Expose-Headers", "isCompensable")
                .header("isCompensable", financeService.isCompensable(document).toString())
                .body("");
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public <T extends Document> ResponseEntity<? extends Document> save(@RequestBody T document) throws Throwable {

        try {
            //Traigo de la base los documentos compensados
            for (int i = 0; i < document.getCompensatedDocuments().size(); i++) {
                document.getCompensatedDocuments()
                        .set(i, documentService.get(document.getCompensatedDocuments().get(i).getId()));
            }

            if (!financeService.isCompensable(document)) {
                document.getCompensatedDocuments().clear();
            }
        } catch (NullPointerException npe) {/*No implementado*/
        }

        document = financeService.save(document);
        return ok()
                .header("id", document.getId().toString())
                .body(document);
    }
}
