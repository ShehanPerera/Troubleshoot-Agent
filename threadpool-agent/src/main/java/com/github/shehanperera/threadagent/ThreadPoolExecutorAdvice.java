package com.github.shehanperera.threadagent;

import com.github.shehanperera.Logger;
import net.bytebuddy.asm.Advice;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadPoolExecutorAdvice {

    @Advice.OnMethodExit
    public static void visitThreadPool(@Advice.Origin String method, @Advice.AllArguments Object[] para) throws
            Exception {

        Logger logger = Logger.getInstance();


        if (method.equals("public java.util.concurrent.ThreadPoolExecutor(int,int,long,java.util.concurrent.TimeUnit,java" +
                ".util.concurrent.BlockingQueue)")) {

            int corePoolSize = (int) para[0];
            int maximumPoolSize = (int) para[1];
            logger.logsOnThreadPool(corePoolSize,maximumPoolSize);

        }

    }
}



