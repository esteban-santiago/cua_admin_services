package com.cua.admin.repositories.accounting.entry;

import com.cua.admin.model.accounting.entries.TemplateEntry;
import com.cua.admin.model.finance.documents.DocumentType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateEntryRepository extends JpaRepository<TemplateEntry, Integer> {

    Optional<TemplateEntry> findByDocumentType(DocumentType documentType);
    
}
