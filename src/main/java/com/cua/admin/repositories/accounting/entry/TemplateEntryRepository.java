package com.cua.admin.repositories.accounting.entry;

import com.cua.admin.model.accounting.documents.DocumentType;
import com.cua.admin.model.accounting.entries.TemplateEntry;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateEntryRepository<T extends TemplateEntry> extends JpaRepository<T, Long> {

    TemplateEntry findByDocumentType(DocumentType documentType);
}
