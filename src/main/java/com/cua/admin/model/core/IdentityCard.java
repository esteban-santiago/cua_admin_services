package com.cua.admin.model.core;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.*;

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
