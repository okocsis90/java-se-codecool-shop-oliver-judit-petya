package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.controller.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCConnectPropParser {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public static ArrayList<String> connectProps() {
        Properties prop = new Properties();
        InputStream input = null;
        ArrayList<String> proplist = new ArrayList<>();
        //if you want to connect to live DB use this line 20th: input = new FileInputStream(pathToLiveDB);
        String pathToLiveDB = "src/main/sql/properties.txt";
        //if you want to connect to live DB use this line 20th: input = new FileInputStream(pathToTestDB);
        String pathToTestDB = "test/testResources/test_properties.txt";
        try {
            input = new FileInputStream(pathToTestDB);

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            proplist.add(prop.getProperty("database"));
            proplist.add(prop.getProperty("username"));
            proplist.add(prop.getProperty("password"));

            logger.info("Database to connect to is: {}, username: {}", prop.getProperty("database"), prop.getProperty("username"));

            return proplist;

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}