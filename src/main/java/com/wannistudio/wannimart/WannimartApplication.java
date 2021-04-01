package com.wannistudio.wannimart;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WannimartApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(WannimartApplication.class)
            .properties("spring.config.location=/home/wannidev0928/appconfig/application.yml")
            .build()
            .run(args);
  }
}
