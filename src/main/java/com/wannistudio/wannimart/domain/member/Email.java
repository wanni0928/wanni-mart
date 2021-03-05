package com.wannistudio.wannimart.domain.member;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.regex.Pattern.matches;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Embeddable
@NoArgsConstructor
public class Email {
    private String email;

    public Email(String email) {
        checkArgument(
                email.length() >= 4 && email.length() <= 50,
                "address length must be between 4 and 50 characters."
        );
        checkArgument(checkAddress(email), "Invalid email address: " + email);
        checkArgument(isNotEmpty(email), "address must be provided.");
        this.email = email;
    }

    private static boolean checkAddress(String address) {
        return matches("[\\w~\\-.+]+@[\\w~\\-]+(\\.[\\w~\\-]+)+", address);
    }

    public String getName() {
        String[] tokens = email.split("@");
        if (tokens.length == 2)
            return tokens[0];
        return null;
    }

    public String getDomain() {
        String[] tokens = email.split("@");
        if (tokens.length == 2)
            return tokens[1];
        return null;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return this.email.equals(email.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
