package com.ipb.projekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjektApplication {
    //TODO: switch to a virtual machine db host running oracle db, or H2 maybe, I dunno
    // but i'd be pretty damn cool if we could deploy a vm and spin up oracle inside during build
    // or maybe docker?
    public static void main(String[] args) {
        SpringApplication.run(ProjektApplication.class, args);
    }

}
