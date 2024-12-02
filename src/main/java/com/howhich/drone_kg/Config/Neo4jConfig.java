package com.howhich.drone_kg.Config;

import com.howhich.drone_kg.Entity.Compound.Authentication;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
//@PropertySource("neo4j.properties")
@ConfigurationProperties(prefix = "spring.neo4j")
public class Neo4jConfig {
    private String uri;
    private Authentication authentication;
//    private String username;
//    private String password;
}
