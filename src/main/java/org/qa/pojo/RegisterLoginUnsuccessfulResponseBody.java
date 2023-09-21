package org.qa.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterLoginUnsuccessfulResponseBody implements BasePojo {

    @JsonProperty("error")
    private String error;

    public RegisterLoginUnsuccessfulResponseBody() { }
    public RegisterLoginUnsuccessfulResponseBody(String error) {

        this.error = error;
    }

    @Override
    public boolean hasValidData(Object object) {

        if(this == object) {

            return true;
        }

        if(!(object instanceof RegisterLoginUnsuccessfulResponseBody responseBody)) {

            return false;
        }

        return this.error.equals(responseBody.error);
    }
}
