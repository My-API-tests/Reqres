package org.data;

public class SingleUser {

    private final int id;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String avatar;
    private final String url;
    private final String text;

    public SingleUser(int id, String email, String firstName, String lastName, String avatar,
                      String url, String text) {

        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.url = url;
        this.text = text;
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

    public String getUrl() {

        return url;
    }

    public String getText() {

        return text;
    }
}
