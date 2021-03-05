package com.wannistudio.wannimart.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import static com.google.common.base.Preconditions.checkNotNull;

@Builder
@Embeddable
@NoArgsConstructor
@Getter
public class Address {
    private String cityStreetAddress;
    private String residentAddress;

    public Address(String cityStreetAddress, String residentAddress) {
        checkNotNull(cityStreetAddress, "cityStreetAddress must be provided");
        checkNotNull(residentAddress, "residentAddress must be provided");
        this.cityStreetAddress = cityStreetAddress;
        this.residentAddress = residentAddress;
    }
}
