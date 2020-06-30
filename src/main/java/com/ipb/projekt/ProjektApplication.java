package com.ipb.projekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjektApplication {
    //TODO: switch to a virtual machine db host running oracle db, or H2 maybe, I dunno
    // but i'd be pretty damn cool if we could deploy a vm and spin up oracle inside during build
    // or maybe docker?

    //TODO: remove the horrible manual css styling of the table, parse the shelf table into a
    // more complex and pre-divided data structure kept in memory and assembled at application start
    // or whenever a shelves are modified, created or deleted
    // or maybe just use a flexbox

    //TODO: replace jquery with plain javascript

    //TODO: divorce the existence of products from being tied to shelves
    // create a separate view for putting on shelves products which exist but aren't on any
    public static void main(String[] args) {
        SpringApplication.run(ProjektApplication.class, args);
    }

}
