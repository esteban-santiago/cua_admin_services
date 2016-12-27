package com.cua.admin.model.accounting.documents.generators;

import com.cua.admin.model.accounting.documents.CreditNoteIssuedDocument;
import com.cua.admin.model.accounting.documents.FlightRecordIssuedDocument;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class DocumentSequenceGenerator extends SequenceStyleGenerator {

    private final SequenceStyleGenerator creditNoteGenerator = new CreditNoteGenerator();
    private final SequenceStyleGenerator flightRecordGenerator = new FlightRecordGenerator();

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        creditNoteGenerator.configure(type, params, serviceRegistry);
        flightRecordGenerator.configure(type, params, serviceRegistry);
        super.configure(type, params, serviceRegistry);
    }

    @Override
    public void registerExportables(Database database) {
        creditNoteGenerator.registerExportables(database);
        flightRecordGenerator.registerExportables(database);
        super.registerExportables(database);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (object instanceof CreditNoteIssuedDocument) {
            return creditNoteGenerator.generate(session, object);
        } else if (object instanceof FlightRecordIssuedDocument) {
            return flightRecordGenerator.generate(session, object);
        }
        return super.generate(session, object);
    }
}
