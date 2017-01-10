package com.cua.admin.model.accounting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author esteban_santiago
 */
@Getter
@RequiredArgsConstructor
public enum AccountingEntryItemType {
    DEBIT("Debe"),
    CREDIT("Haber");
    
    private final String description;
    
}
