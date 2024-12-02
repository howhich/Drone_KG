package com.howhich.drone_kg.Controller;

import com.howhich.drone_kg.Utlis.Neo4jUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/neo4j")
public class TestController {
    @GetMapping("/getNodes")
    public String test()
    {
        String cql = "match (n) return n";
        List m = Neo4jUtils.m(cql);
        for (Object o : m){
            System.out.println(o);
        }
        return "hello";
    }
}
