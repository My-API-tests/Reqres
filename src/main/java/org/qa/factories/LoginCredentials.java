package org.qa.factories;

import org.qa.bodies.Credentials;

public class LoginCredentials {

    public static Credentials correct() {

        return new Credentials("eve.holt@reqres.in", "cityslicka");
    }

    public static Credentials incorrectUsername() {

        return new Credentials("eve.holt@req.in", "pistol");
    }

    public static Credentials withoutEmailOrUsername() {

        return new Credentials("", "pistol");
    }

    public static Credentials withoutPassword() {

        return new Credentials("eve.holt@reqres.in", "");
    }
}
