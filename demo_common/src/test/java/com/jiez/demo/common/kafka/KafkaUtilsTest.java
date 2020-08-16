package com.jiez.demo.common.kafka;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author by Jiez
 * @classname KafkaUtilsTest
 * @description TODO
 * @date 2020/6/7 14:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class KafkaUtilsTest {

    @Test
    public void test() throws InterruptedException {
        new KafkaUtils().kafkaClientConsumer();
    }

}