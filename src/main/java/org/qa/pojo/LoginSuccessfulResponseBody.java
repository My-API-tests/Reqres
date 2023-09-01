package org.qa.pojo;

public class LoginSuccessfulResponseBody {

    private String token;

    public String getToken() {

        return token;
    }

    public boolean hasNotNullValues() {

        return token != null;
    }
}
