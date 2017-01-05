package com.cua.admin.model.core;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@AllArgsConstructor
public class IdentityCard implements Serializable {

    @Enumerated(EnumType.STRING)
    private IdentityCardType identityCardType;

    @NonNull
    private String identityCardNumber;

}
