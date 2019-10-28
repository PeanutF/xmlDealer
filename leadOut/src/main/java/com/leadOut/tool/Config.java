package com.leadOut.tool;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class Config {

    public static String xmlDirectoryPath = null;
    public static String logPath = null;

    public Config(){
        Properties pps = new Properties();
        System.out.println();

        try {
            pps.load(new FileInputStream("src\\main\\resources\\config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration enum1 = pps.propertyNames();//得到配置文件的名字

        xmlDirectoryPath = pps.getProperty("xmlDirectoryPath");
        logPath = pps.getProperty("logPath");

    }

}
