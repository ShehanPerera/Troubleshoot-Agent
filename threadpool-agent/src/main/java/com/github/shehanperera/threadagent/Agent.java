package com.github.shehanperera.threadagent;

import com.github.shehanperera.Logger;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.net.JarURLConnection;
import java.util.jar.JarFile;

/**
 *
 */
public class Agent {

    public static void premain(String arguments, Instrumentation instrumentation) {

        System.out.println("Troubleshoot Agent");

        Logger getLoggers = Logger.getInstance();
        getLoggers.logsOnAgent();
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(new File("logger-1.0-SNAPSHOT.jar"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        instrumentation.appendToBootstrapClassLoaderSearch(jarFile);

        new AgentBuilder.Default()
                .ignore(ElementMatchers.none())
                .type(ElementMatchers.nameContains("ThreadPoolExecutor"))
                .transform((builder, type, classLoader, module) -> builder
                        .visit(Advice.to(ThreadPoolExecutorAdvice.class).on(ElementMatchers.any()))
                ).installOn(instrumentation);
    }

}
