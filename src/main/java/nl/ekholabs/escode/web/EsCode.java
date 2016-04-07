package nl.ekholabs.escode.web;

import java.io.*;
import java.util.Optional;

import javax.swing.JFrame;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EsCode {
  
    public  static String ROOT_GENERATE = "generate-dir";
    public  static String ROOT_PARSE = "parse-dir";

    public static void main(String[] args) {
        SpringApplication.run(EsCode.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return (String[] args) -> {
            new File(ROOT_GENERATE).mkdir();
            new File(ROOT_PARSE).mkdir();
        };
    }
}
