//: com.yulikexuan.cloudlab.sample.CloudSampleApp.java


package com.yulikexuan.cloudlab.sample;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableAsync
@EnableScheduling
@SpringBootApplication
public class CloudSampleApp {

    public static void main(String[] args) {
        SpringApplication.run(CloudSampleApp.class, args);
    }

}///:~
