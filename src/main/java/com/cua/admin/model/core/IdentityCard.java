package com.cua.admin.model.core;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
//@Entity
//@Table(name = "identity_document")
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@AllArgsConstructor
public class IdentityCard implements Serializable {

    @Enumerated(EnumType.STRING)
    private IdentityCardType identityDocumentType;

    @NonNull
    private String identityDocumentNumber;

}
