package com.wannistudio.wannimart.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
