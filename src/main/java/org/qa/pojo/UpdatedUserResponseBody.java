package org.qa.pojo;

import org.qa.bodies.UserBody;

public class UpdatedUserResponseBody extends UserBody implements BasePojo {

    private String updatedAt;

    public String getUpdatedAt() {

        return updatedAt;
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
               updatedAt != null;
    }
}
