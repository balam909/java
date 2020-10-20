package com.eblimon.logback;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/base")
public class BaseServlet {

    @GET
    @Path("/hi")
    @Produces("application/json")
    public ResponseEntity<Welcome> sayHi(){
        Welcome welcome = new Welcome("Hi!","Stranger");
        return new ResponseEntity<Welcome>(welcome, HttpStatus.OK);
    }



}
