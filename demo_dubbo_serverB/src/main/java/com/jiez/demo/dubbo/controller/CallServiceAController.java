package com.jiez.demo.dubbo.controller;

import com.jiez.demo.dubbo.service.IDemoAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Jiez
 * @classname CallServiceAController
 * @description TODO
 * @date 2020/6/21 19:51
 */

@RestController
@RequestMapping(value = "/call/serverA")
public class CallServiceAController {

    @Autowired
    private IDemoAService demoAService;

    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    public String sayHi(String name) {
        String world = demoAService.sayHi(name);
        return "result: " + world;
    }
}
