package com.cua.admin.repositories.accounting.documents;

import com.cua.admin.model.accounting.documents.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository<T extends Document> extends CrudRepository<T, Integer> {

    //List<T> findByDescription(String description);

    T findById(Integer id);

    //T findByDocumentTypeId(Integer id);
}
