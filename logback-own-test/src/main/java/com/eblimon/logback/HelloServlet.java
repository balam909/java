package com.eblimon.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloServlet extends HttpServlet {

    Logger log = LoggerFactory.getLogger(HelloServlet.class);

    //http://localhost:8080/hello?test1=value1&test2=valu2&test3=value3

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        //To set attributes into the log
        setAttributesToMDC(map);
        log.info("Start Servlet execution");
        //To validate thread functionality
        MDC.getCopyOfContextMap();
        executeThreadTask();
        //To generate response
        generateDummyResponse(response);
        log.info("End Servlet execution");
        //To remove attributes into the log
        removeAttributesToMDC(map.keySet());
    }

    private void generateDummyResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println("<h1>Hello from HelloServlet</h1>");
    }

    private void setAttributesToMDC(Map<String, String[]> map){
        log.info("----------------------------------------------------------------------------------------");
        if(map.isEmpty()){
            log.info("No Params Declared");
        }else{
            for(Map.Entry<String, String[]> item : map.entrySet()){
                log.info("key: "+item.getKey()+" value: "+ Arrays.deepToString(item.getValue()));
            }
        }
        log.info("----------------------------------------------------------------------------------------");
        MDC.put("test1",map.get("test1")[0]);
        MDC.put("test2",map.get("test2")[0]);
        MDC.put("test3",map.get("test3")[0]);
    }

    private void removeAttributesToMDC(Set<String> attributes){
        for(String item : attributes){
            MDC.remove(item);
        }
    }

    private void executeThreadTask(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<ThreadClass> threads = new ArrayList<ThreadClass>();
        for(int i = 0 ; i < 10 ; i++){
            ThreadClass threadClass = new ThreadClass(i, MDC.getCopyOfContextMap());
            threads.add(threadClass);
        }
        try {
            executorService.invokeAll(threads);
        } catch (InterruptedException e) {
            log.info("There is an error at thread execution");
            e.printStackTrace();
        }
    }
}
