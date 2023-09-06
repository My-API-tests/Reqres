package org.qa.pojo;

import org.qa.bodies.UserBody;

public class CreatedUserResponseBody extends UserBody implements BasePojo{
    private String id;
    private String createdAt;

    public CreatedUserResponseBody() { }

    public String getId() {

        return id;
    }

    public String getCreatedAt() {

        return createdAt;
    }

    @Override
    public boolean hasValidData(Object object) {

        if (this == object) {

            return true;
        }

        if (!(object instanceof UserBody userBody)) {

            return false;
        }

        return getName().equals(userBody.getName()) && getJob().equals(userBody.getJob()) &&
               id != null && createdAt != null;
    }
}
