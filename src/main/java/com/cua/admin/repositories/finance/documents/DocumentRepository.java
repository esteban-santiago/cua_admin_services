package com.cua.admin.repositories.finance.documents;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.DocumentType;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository<T extends Document> extends JpaRepository<T, Long> {

    List<T> findByPerson(Person person);
    List<T> findByDocumentType(DocumentType documentType);

    Optional<T> findById(Long id);
    Optional<T> findByLegalId(Long id);
    Optional<T> findByReferencedDocumentId(Integer id);

}
