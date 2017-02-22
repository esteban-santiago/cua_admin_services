package com.cua.admin.services.finance;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.exceptions.DocumentNotFoundException;
import com.cua.admin.repositories.finance.documents.DocumentRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author esantiago
 */
@Service
@Transactional
@RequiredArgsConstructor
public class DocumentService {

    @Autowired
    private final DocumentRepository<Document> documentRepository;

    public Document get(Long id) throws Throwable {
        return this.documentRepository.findById(id).orElseThrow(
                () -> new DocumentNotFoundException(id));
    }

    public Document getByLegalId(Long id) throws Throwable {
        return this.documentRepository.findByLegalId(id).orElseThrow(
                () -> new DocumentNotFoundException(id));
    }

    
    public void save(Document document) {
        document.close();
        this.documentRepository.saveAndFlush(document);
    }

    public void delete(Long id) {
        this.documentRepository.delete(id);
    }

    public void delete(Document document) {
        this.delete(document.getId());
    }

}
