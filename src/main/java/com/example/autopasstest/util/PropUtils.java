package com.example.autopasstest.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropUtils {
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = PropUtils.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                log.info("Sorry, unable to find application.properties");
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
