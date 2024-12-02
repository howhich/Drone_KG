package com.howhich.drone_kg.Entity.Compound;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.neo4j.authentication")
public class Authentication {
    private String username;
    private String password;
}
