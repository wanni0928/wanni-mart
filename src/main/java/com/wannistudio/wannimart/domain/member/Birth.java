package com.wannistudio.wannimart.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Embeddable;

import static com.google.common.base.Preconditions.checkNotNull;

@Embeddable
@Getter
@NoArgsConstructor
public class Birth {
    private Integer year;
    private Integer month;
    private Integer day;

    public Birth(Integer year, Integer month, Integer day) {
        checkNotNull(year, "birth year must be provided");
        checkNotNull(month, "birth month must be provided");
        checkNotNull(day, "birth day must be provided");

        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("year", year)
                .append("month", month)
                .append("day", day)
                .toString();
    }
}
