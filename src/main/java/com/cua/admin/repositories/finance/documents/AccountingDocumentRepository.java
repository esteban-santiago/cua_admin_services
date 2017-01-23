package com.cua.admin.repositories.finance.documents;

import com.cua.admin.model.finance.Document;
import com.cua.admin.model.finance.DocumentType;
import com.cua.admin.model.core.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingDocumentRepository<T extends Document> extends JpaRepository<T, Long> {

    List<Document> findByMember(Member member);
    List<Document> findByDocumentType(DocumentType documentType);

    T findById(Long id);

    //T findByDocumentTypeId(Integer id);
}
