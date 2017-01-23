package com.cua.admin.repositories.accounting.entry;

import com.cua.admin.model.finance.DocumentType;
import com.cua.admin.model.accounting.entries.TemplateEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateEntryRepository extends JpaRepository<TemplateEntry, Integer> {

    TemplateEntry findByDocumentType(DocumentType documentType);
    
}
