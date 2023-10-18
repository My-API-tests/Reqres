package org.qa.factories;

import org.qa.bodies.Credentials;

public class RegisterCredentials {

    public static Credentials correct() {

        return new Credentials("eve.holt@reqres.in", "pistol");
    }

    public static Credentials notDefinedUser() {

        return new Credentials("eve.holt@req.in", "pistol");
    }

    public static Credentials withoutEmailOrUsername() {

        return new Credentials("", "pistol");
    }

    public static Credentials withoutPassword() {

        return new Credentials("eve.holt@reqres.in", "");
    }
}
