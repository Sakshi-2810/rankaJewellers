package com.example.jewelry.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        String uri = "mongodb+srv://dbUser:mobf7OsqjTBQBvh4@cluster0.zfdmvwa.mongodb.net/?appName=Cluster0&retryWrites=true&w=majority";
        return MongoClients.create(uri);
    }
}

