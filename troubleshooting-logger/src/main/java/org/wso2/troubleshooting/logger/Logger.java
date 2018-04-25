/*
*  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing,
*  software distributed under the License is distributed on an
*  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*  KIND, either express or implied.  See the License for the
*  specific language governing permissions and limitations
*  under the License.
*/
package org.wso2.troubleshooting.logger;

/**
 * This is logger using for get details of threads
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static Logger logger = new Logger();
    File Dir;
    File file;
    FileWriter fileWriter;
    DateFormat dateFormat;
    Date date = new Date();

    private Logger() {

        Dir = new File("../logs");
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
            error(e);
        }
    }

    public void log(String message) {

        try {
            fileWriter.write(dateFormat.format(date) + "  " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stoplog() {

        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void error(Exception message) {

        StackTraceElement[] elements = message.getStackTrace();
        try {
            fileWriter.write("Error :" + message.toString());
            fileWriter.write("\n");
            for (int i = 1; i < elements.length; i++) {
                StackTraceElement s = elements[i];

                fileWriter.write(dateFormat.format(date) + "\tat " + s.getClassName() + "." + s.getMethodName() + "("
                        + s.getFileName() + ":"
                        + s.getLineNumber() + ")\n");
            }
            fileWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}