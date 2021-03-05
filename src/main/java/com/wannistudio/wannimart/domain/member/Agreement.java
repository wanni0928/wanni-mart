package com.wannistudio.wannimart.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import static com.google.common.base.Preconditions.checkArgument;

@Embeddable
@Getter
@NoArgsConstructor
public class Agreement {
    private boolean termsOfUse;
    private boolean personalInfoUsage;
    private boolean marketingUsage;
    private boolean isAdult;

    public Agreement(boolean termsOfUse, boolean personalInfoUsage, boolean marketingUsage, boolean isAdult) {
        checkArgument(termsOfUse, "termsOfUse must be true");
        checkArgument(personalInfoUsage, "termsOf personalInfoUsage must be true");

        this.termsOfUse = termsOfUse;
        this.personalInfoUsage = personalInfoUsage;
        this.marketingUsage = marketingUsage;
        this.isAdult = isAdult;
    }
}
