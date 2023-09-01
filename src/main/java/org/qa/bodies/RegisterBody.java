package org.qa.bodies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterBody {

    private final String email;
    private final String password;

    public RegisterBody(String email, String password) {

        this.email = email;
        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public String getPassword() {

        return password;
    }
}
