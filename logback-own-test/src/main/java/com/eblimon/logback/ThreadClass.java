package com.eblimon.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

public class ThreadClass implements Callable<String> {

    Logger log = LoggerFactory.getLogger(ThreadClass.class);

    private int threadNumber;
    private Map<String, String> mdcContext;

    public ThreadClass(int threadNumber, Map<String, String> mdcContext) {
        if(threadNumber > 10){
            threadNumber = 10;
        }else if(threadNumber < 0){
            threadNumber = 0;
        }
        this.threadNumber = threadNumber;
        this.mdcContext = mdcContext;
    }

    public String call() throws Exception {
        MDC.setContextMap(mdcContext);
        log.info("This a thread execution for thread number {}",threadNumber);
        int secondsToWait=10 - threadNumber;
        log.info("I'll wait for {} seconds to continue",secondsToWait);
        Thread.sleep(secondsToWait*1000);
        log.info("I waited for {} seconds, I'm going out of here",secondsToWait);
        return null;
    }
}
