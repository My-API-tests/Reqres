package org.qa.pojo;

public class LoginSuccessfulResponseBody implements BasePojo {

    private String token;

    public String getToken() {

        return token;
    }

    @Override
    public boolean hasNotNullValues() {

        return token != null;
    }
}
