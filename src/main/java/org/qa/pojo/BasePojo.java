package org.qa.pojo;

public interface BasePojo {

    default boolean hasValidData(Object object) { return true; }

    default boolean hasNotNullValues() { return true; }
}
