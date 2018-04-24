package com.github.shehanperera.threadagent;

import net.bytebuddy.asm.Advice;

import java.util.Arrays;

public class MyAdvice {

    @Advice.OnMethodEnter
    static void get(@Advice.Origin String method, @Advice.AllArguments Object[] para) {

        System.out.println(method);
        System.out.println(String.valueOf(Arrays.asList(Thread.currentThread().getStackTrace())));

//        System.out.println(String.valueOf(Arrays.asList(Thread.currentThread().getStackTrace())));
//        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
//        for (int i = 1; i < elements.length; i++) {
//            StackTraceElement s = elements[i];
//            System.out.println("\tat " + s.getClassName() + "." + s.getMethodName() + "(" + s.getFileName() + ":"
//                    + s.getLineNumber() + ")");
//        }

    }

}

