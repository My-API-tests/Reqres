package org.qa.bodies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBody {

    protected String name;
    protected String job;

    public UserBody() { }
    public UserBody(String name, String job) {

        this.name = name;
        this.job = job;
    }
}
