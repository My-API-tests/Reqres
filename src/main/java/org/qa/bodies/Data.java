package org.qa.bodies;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

    private int id;
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String avatar;

    public Data() { }
    public Data(int id, String email, String firstName, String lastName, String avatar) {

        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public int getId() {

        return id;
    }

    public String getEmail() {

        return email;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public String getAvatar() {

        return avatar;
    }

    @Override
    public boolean equals(Object object) {

        if(this == object) {

            return true;
        }

        if(!(object instanceof Data data)) {

            return false;
        }

        return this.id == data.id && this.email.equals(data.email) &&
               this.firstName.equals(data.firstName) && this.lastName.equals(data.lastName) &&
               this.avatar.equals(data.avatar);
    }
}
