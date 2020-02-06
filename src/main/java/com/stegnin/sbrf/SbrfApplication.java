package com.stegnin.sbrf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class SbrfApplication implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SbrfApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            int port = applicationContext.getBean(Environment.class).getProperty("server.port", Integer.class, 8080);
            String localhost = ip.concat(":").concat(String.valueOf(port));

            System.out.printf("API started on %s \n", localhost);
            System.out.printf("To shutdown send GET request to %s", localhost.concat("/shutdown"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
