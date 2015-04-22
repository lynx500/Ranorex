package com.ranorex.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyLoader {
    public static String getBrowserName() throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(new File("src/test/resources/config.properties")));
        return props.getProperty("browser");
    }

    public static String getBaseURL() throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(new File("src/test/resources/config.properties")));
        return props.getProperty("baseUrl");
    }
}
