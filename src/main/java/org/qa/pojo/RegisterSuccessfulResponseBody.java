package org.qa.pojo;

public class RegisterSuccessfulResponseBody implements BasePojo {

    private String id;
    private String token;

    public String getId() {

        return id;
    }

    public String getToken() {

        return token;
    }

    @Override
    public boolean hasNotNullValues() {

        return id != null && token != null;
    }
}
