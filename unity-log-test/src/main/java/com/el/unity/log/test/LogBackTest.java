package com.el.unity.log.test;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2015/11/30.
 */
public class LogBackTest {

    public static void load(String fileName) throws IOException, JoranException {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        String file =  LogBackTest.class.getClassLoader().getResource("").getFile()+fileName;
        File externalConfigFile = new File(file);
        if (!externalConfigFile.exists()) {
            throw new IOException("Logback External Config File Parameter does not reference a file that exists");
        } else {
            if (!externalConfigFile.isFile()) {
                throw new IOException("Logback External Config File Parameter exists, but does not reference a file");
            } else {
                if (!externalConfigFile.canRead()) {
                    throw new IOException("Logback External Config File exists and is a file, but cannot be read.");
                } else {
                    JoranConfigurator configurator = new JoranConfigurator();
                    configurator.setContext(lc);
                    lc.reset();
                    configurator.doConfigure(file);
                    StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
                }
            }
        }
    }

    public static void main(String args[]) {
        try {
            load("logback.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JoranException e) {
            e.printStackTrace();
        }
        Logger log= LoggerFactory.getLogger(LogBackTest.class);
        int i=0;
        while(true){
            try{
                System.out.println(i++);
                log.debug("Hello");
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    }
}
