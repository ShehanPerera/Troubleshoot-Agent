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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is logger using for get details of threads
 */
public class Logger {

    private static Logger logger = new Logger();
    private final File dir = new File("../logs");
    private final File file = new File(dir, "troubleshoot.log");
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
    private final FileWriter fileWriter;
    private Date date;

    private Logger() {

        dir.mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileWriter = new FileWriter(file.getAbsoluteFile(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Logger getInstance() {

        return logger;
    }

    public void info(String message) {

        try {
            date = new Date();
            fileWriter.write(dateFormat.format(date) + " INFO : " + message);
            fileWriter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void error(String message) {

        try {
            fileWriter.write("");
            date = new Date();
            fileWriter.write(dateFormat.format(date) + " ERROR : " + message);
            fileWriter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void stoplog() {

        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
