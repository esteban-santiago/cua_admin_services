package com.cua.admin.services.finance;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.DocumentStatus;
import com.cua.admin.model.finance.documents.DocumentType;
import com.cua.admin.model.finance.documents.exceptions.DocumentNotFoundException;
import com.cua.admin.repositories.finance.documents.DocumentRepository;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentService {

    @Autowired
    private DocumentRepository<Document> documentRepository;

    //@SuppressWarnings("unchecked")
    public <T extends Document> T get(Long id) throws Throwable {
        return (T) this.documentRepository.findById(id)
            .orElseThrow(() -> new DocumentNotFoundException(id));
    }

    public List<? extends Document> getAllByType(DocumentType type) {
        return this.documentRepository.findByDocumentType(type);
    }

    @SuppressWarnings("unchecked")
    public <T extends Document> T getByReferencedDocumentId(Integer id) throws DocumentNotFoundException {
        return (T) this.documentRepository.findByReferencedDocumentId(id)
            .orElseThrow(() -> new DocumentNotFoundException((long) id));
    }

    public List<? extends Document> getAllByPerson(Integer person_id) {
        return this.documentRepository.findByPersonId(person_id);
    }

    public List<Document> getAllCompensated() {
        return this.documentRepository.findByStatus(DocumentStatus.COMPENSATED);
    }

    public List<Document> getAllOpened() {
        return this.documentRepository.findByStatus(DocumentStatus.OPENED);
    }

    public List<Document> getAllCompensables() {
        return this.documentRepository.findByDocumentTypeIn(DocumentType.getCompensables());
    }

    public List<Document> getAllCompensators() {
        return this.documentRepository.findByDocumentTypeIn(DocumentType.getCompensators());
    }

    public List<Document> getAll() {
        return this.documentRepository.findAll();
    }

//    public Document getByLegalId(Long id) throws Throwable {
//        return this.documentRepository.findByLegalId(id)
//            .orElseThrow(() -> new DocumentNotFoundException(id));
//    }

    public <T extends Document> T save(T document) {
        return this.documentRepository.saveAndFlush(document);
    }

    public void delete(Long id) {
        this.documentRepository.deleteById(id);
    }

    public void delete(Document document) {
        this.delete(document.getId());
    }

}
