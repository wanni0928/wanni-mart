package com.wannistudio.wannimart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WannimartApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(WannimartApplication.class)
            .properties("D:\\Users\\chju\\qqq.yml")
            .run(args);
  }

}
