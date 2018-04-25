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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunThreads {

    // Maximum number of threads in thread pool
    static final int MAX_TASK = 3;

    public static void main(String[] args) {

        // creates a tasks
        Runnable r1 = new Task("task 1");

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_TASK);

        // passes the Task objects to the pool to execute (Step 3)
        pool.execute(r1);

        // pool shutdown ( Step 4)
        pool.shutdown();

    }

}