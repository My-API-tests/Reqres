package org.qa.pojo;

import org.qa.bodies.UserBody;

public class UpdatedUserResponseBody extends UserBody {

    private String updatedAt;

    public String getUpdatedAt() {

        return updatedAt;
    }

    public boolean hasValidData(UserBody userBody) {

        return getName().equals(userBody.getName()) && getJob().equals(userBody.getJob()) &&
               updatedAt != null;
    }
}
