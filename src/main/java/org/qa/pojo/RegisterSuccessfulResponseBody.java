package org.qa.pojo;

public class RegisterSuccessfulResponseBody {

    private String id;
    private String token;

    public String getId() {

        return id;
    }

    public String getToken() {

        return token;
    }

    public boolean hasNotNullValues() {

        return id != null && token != null;
    }
}
