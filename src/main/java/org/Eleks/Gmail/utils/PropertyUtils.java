package org.Eleks.Gmail.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    private static String propertiesFileLocation = new String("src/main/resources/testData.properties");
    public static String getProperties(String property) {
        //Getting needed property from the ".properties" file
        Properties properties = new Properties();
        {
            try {
                FileInputStream inputStream = new FileInputStream(propertiesFileLocation);
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("FILE with  or selected property are missing, PLEASE check your Property File");
            }
        }
        return String.valueOf(properties.get(property));
    }

}
