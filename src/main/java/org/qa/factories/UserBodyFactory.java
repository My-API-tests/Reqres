package org.qa.factories;

import org.qa.bodies.UserBody;

public class UserBodyFactory {

    public static UserBody correct() {

        return new UserBody("Andy", "Miner");
    }

    public static UserBody toUpdate_PUT() {

        return new UserBody("Andy", "Programmer");
    }

    public static UserBody toUpdate_PATCH() {

        return new UserBody("Andy", "Musican");
    }
}
