package com.cua.admin.model.accounting.documents.generators;

import org.hibernate.MappingException;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.util.Properties;

class FlightRecordGenerator extends SequenceStyleGenerator {

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        params.setProperty("initial_value", "9000");
        params.setProperty("sequence_name", "document_flight_record_issued_id_seq");
        super.configure(type, params, serviceRegistry);
    }
}
