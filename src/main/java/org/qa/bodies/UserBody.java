package org.qa.bodies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBody {

    protected String name;
    protected String job;

    public UserBody() { }
    public UserBody(String name, String job) {

        this.name = name;
        this.job = job;
    }

    public String getName() {

        return name;
    }

    public String getJob() {

        return job;
    }
}
