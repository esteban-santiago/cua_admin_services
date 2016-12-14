/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.repositories.accounting.documents;


import com.cua.admin.model.accounting.documents.*;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author esteban_santiago
 * @param <T>
 */
public interface DocumentRepository<T extends Document> extends CrudRepository<T, Integer> {

    /**
     *
     * @param description
     * @return
     */
    //List<T> findByDescription(String description);

    /**
     *
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     *
     * @param id
     * @return
     */
    //T findByDocumentTypeId(Integer id);
}
