package org.qa.factories;

import org.qa.pojo.RegisterLoginUnsuccessfulResponseBody;

public class UnsuccessfulResponseBodyFactory {

    public static RegisterLoginUnsuccessfulResponseBody forDefinedUsersOnly() {

        return new RegisterLoginUnsuccessfulResponseBody("Note: Only defined users succeed registration");
    }

    public static RegisterLoginUnsuccessfulResponseBody missingEmailOrUsername() {

        return new RegisterLoginUnsuccessfulResponseBody("Missing email or username");
    }

    public static RegisterLoginUnsuccessfulResponseBody missingPassword() {

        return new RegisterLoginUnsuccessfulResponseBody("Missing password");
    }

    public static RegisterLoginUnsuccessfulResponseBody userNotFound() {

        return new RegisterLoginUnsuccessfulResponseBody("user not found");
    }
}
