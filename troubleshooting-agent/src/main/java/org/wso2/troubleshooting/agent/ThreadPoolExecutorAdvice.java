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
package org.wso2.troubleshooting.agent;

import net.bytebuddy.asm.Advice;
import org.wso2.troubleshooting.logger.Logger;

/**
 * This class for instrument ThreadPoolExecutor Class
 */
public class ThreadPoolExecutorAdvice {

    @Advice.OnMethodExit
    public static void visitThreadPoolExecutor(@Advice.Origin String method, @Advice.AllArguments Object[] para) throws
            Exception {

        Logger logger = Logger.getInstance();

        if (method.equals("public java.util.concurrent.ThreadPoolExecutor(int,int,long,java.util.concurrent.TimeUnit,java" +
                ".util.concurrent.BlockingQueue)")) {

            int corePoolSize = (int) para[0];
            int maximumPoolSize = (int) para[1];
            logger.log("ThreadPool Details :\n");
            logger.log("  CorePoolSize is : " + corePoolSize + "\n");
            logger.log("  MaximumPoolSize is :" + maximumPoolSize + "\n");
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            for (int i = 1; i < elements.length; i++) {
                StackTraceElement s = elements[i];
                logger.log("\tat " + s.getClassName() + "." + s.getMethodName() + "(" + s.getFileName() + ":"
                        + s.getLineNumber() + ")\n");
            }

            logger.stoplog();
        }

    }
}



