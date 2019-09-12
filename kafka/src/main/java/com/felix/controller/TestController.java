package com.felix.controller;

import com.felix.kafka.Producer;
import com.felix.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class TestController {

    @Autowired
    private Producer producer;

    @GetMapping(value = "/send")
    public String send() throws Exception{
        TestVo testVo = new TestVo();
        testVo.setAge(12);
        testVo.setCity("shanghai");
        testVo.setUsername("caoming");
        producer.send(testVo);
        return "{\"code\":0}";
    }
}
