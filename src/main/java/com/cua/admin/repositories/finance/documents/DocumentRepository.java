package com.cua.admin.repositories.finance.documents;

import com.cua.admin.model.core.Person;
import com.cua.admin.model.finance.documents.Document;
import com.cua.admin.model.finance.documents.DocumentStatus;
import com.cua.admin.model.finance.documents.DocumentType;
import com.cua.admin.model.finance.documents.FlightRecordIssued;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DocumentRepository<T extends Document> extends JpaRepository<T, Long> {

    List<T> findByPerson(Person person);

    List<T> findByPersonId(Integer person_id);

    List<T> findByDocumentType(DocumentType documentType);

    List<T> findByDocumentTypeIn(Set<DocumentType> documentTypes);

    //Optional<T> findById(Long id);

    Optional<FlightRecordIssued> findFlightRecordIssuedByLegalId(Long id);

    Optional<T> findByReferencedDocumentId(Integer id);
    
    List<T> findByStatus(DocumentStatus status);

    //Optional<List<T>> findByIsOpened();

}
