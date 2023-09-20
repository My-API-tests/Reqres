package org.qa.pojo;

public class RegisterLoginUnsuccessfulResponseBody implements BasePojo {

    private String message;

    public RegisterLoginUnsuccessfulResponseBody() { }
    public RegisterLoginUnsuccessfulResponseBody(String error) {

        this.message = error;
    }

    @Override
    public boolean hasValidData(Object object) {

        if(this == object) {

            return true;
        }

        if(!(object instanceof RegisterLoginUnsuccessfulResponseBody responseBody)) {

            return false;
        }

        return this.message.equals(responseBody.message);
    }
}
