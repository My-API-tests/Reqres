package org.qa.pojo;

import org.qa.bodies.UserBody;

public class CreatedUserResponseBody extends UserBody {
    private String id;
    private String createdAt;

    public CreatedUserResponseBody() { }

    public String getId() {

        return id;
    }

    public String getCreatedAt() {

        return createdAt;
    }

    public boolean hasValidData(UserBody userBody) {

        return getName().equals(userBody.getName()) && getJob().equals(userBody.getJob()) &&
               id != null && createdAt != null;
    }
}
