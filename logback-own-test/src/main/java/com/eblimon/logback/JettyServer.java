package com.eblimon.logback;

import ch.qos.logback.access.jetty.RequestLogImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import java.net.InetSocketAddress;

public class JettyServer extends AbstractFactoryBean<Server> {

    private static Logger log = LoggerFactory.getLogger(JettyServer.class);

    public Class<?> getObjectType() {
        return Server.class;
    }

    protected Server createInstance() {
        Server server = new Server(new InetSocketAddress(System.getProperty("jetty.http.host"), Integer.parseInt(System.getProperty("jetty.http.port"))));
        /**** All this is done by new Server(new InetSocketAddress(host,port))
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(Integer.parseInt(System.getProperty("jetty.http.port")));
        serverConnector.setHost(System.getProperty("jetty.http.host"));
        server.setConnectors(new Connector[] {serverConnector});
         ****/
        //RequestLogImpl requestLog = new RequestLogImpl();
        //requestLog.setFileName(System.getProperty("jetty.home")+"/conf/resources/logback.xml");
        //RequestLogHandler logbackHandler = new RequestLogHandler();
        //logbackHandler.setRequestLog(requestLog);
        HandlerCollection collectionHandler = new HandlerCollection();
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(HelloServlet.class,"/hello");

        log.info("--------------------------------------------------------------------------------------------------");
        log.info("----------------------------------------Adding handlers-------------------------------------------");
        log.info("--------------------------------------------------------------------------------------------------");
        //collectionHandler.addHandler(logbackHandler);
        collectionHandler.addHandler(new HelloHandler());
        collectionHandler.addHandler(servletHandler);

        collectionHandler.setServer(server);
        server.setHandler(collectionHandler);

        return server;
    }
}
