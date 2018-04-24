package com.github.shehanperera;

/**
 * Hello world!
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class Logger {

    private static Logger logger = new Logger();
    File Dir;
    File file;
    FileWriter fileWriter;
    DateFormat dateFormat;
    Date date = new Date();

    private Logger() {

        Dir = new File("logs");
        file = new File(Dir, "troubleshoot.log");
        createFile();
        try {
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        date = new Date();
    }

    public static Logger getInstance() {

        return logger;
    }

    public void createFile() {

        Dir = new File("../logs");
        Dir.mkdir();
        file = new File(Dir, "troubleshoot.log");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logsOnAgent() {

          try {
            fileWriter.write("System Details : \n");
            Enumeration<String> propertyNames = (Enumeration<String>) System.getProperties().propertyNames();
            while (propertyNames.hasMoreElements()) {
                String propName = propertyNames.nextElement();
                fileWriter.write(dateFormat.format(date) + " INFO-\tat " + propName + " = " + System.getProperty
                        (propName) + "\n");

            }
            fileWriter.write("Thread Details :\n");
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            for (int i = 1; i < elements.length; i++) {
                StackTraceElement s = elements[i];
                fileWriter.write(dateFormat.format(date) + " INFO-\tat " + s.getClassName() + "." + s.getMethodName
                        () + "(" + s.getFileName() + ":"
                        + s.getLineNumber() + ")\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
          finally {
              try {
                  fileWriter.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }

    }
    public void logsOnThreadPool(int corePoolSize,int maximumPoolSize) {


        try {
            fileWriter.write("ThreadPool Details: \n");
            fileWriter.write(dateFormat.format(date) + " INFO - CorePoolSize is : " + corePoolSize + "\n");
            fileWriter.write(dateFormat.format(date) + " INFO - MaximumPoolSize is : " + maximumPoolSize + "\n");

            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            for (int i = 1; i < elements.length; i++) {
                StackTraceElement s = elements[i];
                fileWriter.write(dateFormat.format(date) + " INFO -\tat " + s.getClassName() + "." + s.getMethodName() + "(" + s.getFileName() + ":"
                        + s.getLineNumber() + ")\n");
            }
            fileWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}