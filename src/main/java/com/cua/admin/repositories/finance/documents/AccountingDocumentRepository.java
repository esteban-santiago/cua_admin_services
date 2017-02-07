package com.cua.admin.repositories.finance.documents;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.DocumentType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingDocumentRepository<T extends Document> extends JpaRepository<T, Long> {

    List<Document> findByPerson(Person person);
    List<Document> findByDocumentType(DocumentType documentType);

    T findById(Long id);
}
