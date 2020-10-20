package com.eblimon.logback;

import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartupClass {

    private static Logger log = LoggerFactory.getLogger(StartupClass.class);

    public static void main(String[] args) throws Exception {
        log.info("Exte es el inicio del metodo main");
        log.info("user.dir -->"+System.getProperty("user.dir"));
        log.info("jetty.home -->"+System.getProperty("jetty.home"));
        log.info("jetty.http.host -->"+System.getProperty("jetty.http.host"));
        log.info("jetty.http.port -->"+System.getProperty("jetty.http.port"));
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Server server = (Server) context.getBean("server");
        server.start();
        //server.dumpStdErr();
        server.join();
    }

}
