package com.example.autopasstest.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropUtils {
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = PropUtils.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
            }else{
                // 載入屬性列表
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
