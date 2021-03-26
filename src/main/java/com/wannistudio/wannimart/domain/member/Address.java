package com.wannistudio.wannimart.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("cityStreetAddress", cityStreetAddress)
                .append("residentAddress", residentAddress)
                .toString();
    }
}
