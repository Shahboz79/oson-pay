package uz.pdp.frontservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FrontServiceApplication {

    public static void main(String[] args) {
        SpringApplication.
                run(FrontServiceApplication.class, args);
    }

}
