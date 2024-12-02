package com.howhich.drone_kg.Utlis;


import com.alibaba.fastjson.JSONObject;
import com.howhich.drone_kg.Config.Neo4jConfig;
import org.neo4j.driver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @ClassDescription:
 * @Author：李白
 * @Date：2023/8/3 14:45
 */
@Component
public class Neo4jUtils {
    @Autowired
    Neo4jConfig neo4jCfg;
    private static Neo4jConfig neo4jConfig;
    @PostConstruct
    public void s(){
        neo4jConfig = neo4jCfg;
    }

    /**
     * 增删改接口调用（create/delete/set）
     * @param cql
     */
    public static void cds(String cql){
        Driver d = GraphDatabase.driver(neo4jConfig.getUri(), AuthTokens.basic(neo4jConfig.getAuthentication().getUsername(), neo4jConfig.getAuthentication().getPassword()));
        Session s = d.session();
        s.run(cql);
        s.close();
        d.close();
    }

    /**
     * 查询接口调用(match)
     * @param cql
     * @return
     */
    public static List m(String cql){

        Driver d = GraphDatabase.driver(neo4jConfig.getUri(), AuthTokens.basic(neo4jConfig.getAuthentication().getUsername(), neo4jConfig.getAuthentication().getPassword()));
        Session s = d.session();
        Result result = s.run(cql);
        List list = new ArrayList();
        //获取每一个节点
        while ( result.hasNext() )
        {
            Record record = result.next();
            Map<String, Object> map = record.get("n").asMap();
            Set<Entry<String, Object>> set = map.entrySet();
            JSONObject nodeProperties = new JSONObject();
            //遍历每个节点中的信息，都是key-value形式的
            for(Map.Entry one : set){
                nodeProperties.put(one.getKey().toString(),one.getValue());
                System.out.print(one.getKey() + "=" + one.getValue() + ";");
            }
            System.out.println();
            list.add(nodeProperties);
        }
        list.stream().sorted();
        System.out.println(list);
        s.close();
        d.close();
        return list;
    }

}

