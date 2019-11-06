package com.baizhi;

import io.goeasy.GoEasy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CmfzApplicationTests {

    @Test
    public void pub() {
        //第一个参数：REST Host
        //第二个参数：发布消息的App Key
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-e891702436bc4f33b60b17739844b6b2");
        //第一个参数：channel的名称
        //第二个参数：发布的内容
        goEasy.publish("channel001", "Hello, GoEasy! my name is zhangsan ,how are you");
    }

}
