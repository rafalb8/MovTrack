package com.movtrack;

import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@PWA(name = "MovTrack - Track watched Movies and Tv Shows", shortName = "MovTrack")
public class MovTrackApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(MovTrackApp.class, args);
    }
}