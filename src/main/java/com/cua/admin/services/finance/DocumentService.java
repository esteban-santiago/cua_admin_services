package com.cua.admin.services.finance;

import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.DocumentType;
import com.cua.admin.model.finance.documents.exceptions.DocumentNotFoundException;
import com.cua.admin.repositories.finance.documents.DocumentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentService {
    
    @Autowired
    private DocumentRepository<Document> documentRepository;

    public <T extends Document> T get(Long id) throws Throwable {
        return (T) this.documentRepository.findById(id)
            .orElseThrow(() -> new DocumentNotFoundException(id));
    }

    public List<? extends Document> getAllByType(DocumentType type) {
        return this.documentRepository.findByDocumentType(type);
    }
    
    public List<? extends Document> getAllByPerson(Integer person_id) {
        return this.documentRepository.findByPerson_Id(person_id);
    }

    public List<? extends Document> getAll() {
        return this.documentRepository.findAll();
    }

//    public Document getByLegalId(Long id) throws Throwable {
//        return this.documentRepository.findByLegalId(id)
//            .orElseThrow(() -> new DocumentNotFoundException(id));
//    }


    public <T extends Document> void save(T document) {
        document.compensate();
        this.documentRepository.saveAndFlush(document);
    }

    public void delete(Long id) {
        this.documentRepository.delete(id);
    }

    public void delete(Document document) {
        this.delete(document.getId());
    }

}
