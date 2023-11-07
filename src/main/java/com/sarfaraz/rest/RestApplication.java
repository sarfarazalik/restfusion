package com.sarfaraz.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            String plainCreds = "email:paword";
            byte[] plainCredsBytes = plainCreds.getBytes();
            byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
            String base64Creds = new String(base64CredsBytes);

            List<String> urls = new urls().getUrls();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            headers.add("Content-Type", "application/vnd.oracle.adf.action+json");

            Map<String, Object> map = new HashMap<>();
            map.put("name", "createNextTask");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

            RestTemplate restTemplate = new RestTemplate();

            for (int i = 0; i< urls.size(); i++) {
                ResponseEntity<String> response = restTemplate.exchange(urls.get(i), HttpMethod.POST, entity, String.class);
                System.out.println(urls.get(i));
            }
            System.out.println("Sarfaraz");
        };
    }
}
