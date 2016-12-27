package com.cua.admin.model.accounting.documents.generators;

import org.hibernate.MappingException;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.util.Properties;

class CreditNoteGenerator extends SequenceStyleGenerator {

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        params.setProperty("initial_value", "8000");
        params.setProperty("sequence_name", "document_credit_note_issued_id_seq");
        super.configure(type, params, serviceRegistry);
    }
}
