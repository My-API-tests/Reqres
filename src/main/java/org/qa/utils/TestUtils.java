package org.qa.utils;

import com.google.common.collect.ImmutableMap;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class TestUtils {

    public static void setAllureEnvironment() {

        allureEnvironmentWriter(

                ImmutableMap.<String, String>builder()
                        .put("Available processors (core)", String.valueOf(Runtime.getRuntime().availableProcessors()))
                        .put("Maximum memory", String.valueOf(Runtime.getRuntime().maxMemory()))
                        .put("Total memory", String.valueOf(Runtime.getRuntime().totalMemory()))
                        .put("Free memory", String.valueOf(Runtime.getRuntime().freeMemory()))
                        .put("Available processors", String.valueOf(Runtime.getRuntime().availableProcessors()))
                        .put("System property", System.getProperty("user.dir"))
                        .put("Operating system", System.getProperty("os.name") + " " + System.getProperty("os.arch"))
                        .put("Java runtime version", System.getProperty("java.runtime.version"))
                        .put("URL", "https://requres.in/")
                        .build()
        );
    }
}
