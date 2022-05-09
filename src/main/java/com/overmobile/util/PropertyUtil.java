package com.overmobile.util;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(PropertyUtil.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            //it should be logged  but now...
            e.printStackTrace();
        }
    }

    public static String getProperty(String propName) {
        return properties.getProperty(propName);
    }
}
