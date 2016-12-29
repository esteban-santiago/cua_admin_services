package com.cua.admin.repositories.accounting.documents;

import com.cua.admin.model.accounting.documents.Document;
import com.cua.admin.model.core.Member;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository<T extends Document> extends CrudRepository<T, Long> {

    List<Document> findByMember(Member member);
    //List<T> findByDescription(String description);

    //T findById(Integer id);

    //T findByDocumentTypeId(Integer id);
}
