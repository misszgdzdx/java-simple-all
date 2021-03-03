package com.github.io.websocket;

import com.alibaba.fastjson.JSONObject;
import com.github.io.websocket.entity.UserTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class JavaSimpleWebsocketApplicationTests {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void test() {
        System.out.println("这是一个测试方法");
    }

    @Test
    void findMongodb() {
        System.out.println("--------------------------------------");
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is("username");
        query.addCriteria(criteria);
        query.with(Sort.by(Sort.Order.asc("name")));
        List<UserTest> list = mongoTemplate.find(query, UserTest.class, "test");
        for (UserTest userTest : list) {
            System.out.println(JSONObject.toJSONString(userTest));
        }
        System.out.println("--------------------------------------");
    }

    @Test
    void insertMongodb() {
        System.out.println("--------------------------------------");
        UserTest userTest = new UserTest();
        userTest.setName("username");
        userTest.setAge(15);
        mongoTemplate.insert(userTest, "test");
        System.out.println("--------------------------------------");
    }

}
