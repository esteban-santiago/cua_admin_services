/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cua.admin.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

//import java.util.Date;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate date) {
        //Instant instant = ;
         return date == null ? null : java.sql.Date.valueOf(date);
    }

    @Override
    public LocalDate convertToEntityAttribute(java.sql.Date date) {
        //Instant instant = value.toInstant();
        return date == null ? null : date.toLocalDate();
    }
}