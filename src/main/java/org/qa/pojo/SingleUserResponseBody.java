package org.qa.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.qa.bodies.Data;
import org.qa.bodies.Support;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleUserResponseBody implements BasePojo {

    private Data data;
    private Support support;

    public SingleUserResponseBody() { }

    public SingleUserResponseBody(Data data, Support support) {

        this.data = data;
        this.support = support;
    }

    public Data getData() {

        return data;
    }

    public Support getSupport() {

        return support;
    }

    @Override
    public boolean hasValidData(Object object) {

        if(this == object) {

            return true;
        }

        if(!(object instanceof SingleUserResponseBody singleUserResponseBody)) {

            return false;
        }

        return this.data.equals(singleUserResponseBody.data) && this.support.equals(singleUserResponseBody.support);
    }
}
