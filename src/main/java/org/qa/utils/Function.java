package org.qa.utils;

@FunctionalInterface
public interface Function<A, B> {

    B getObject(A response);
}
