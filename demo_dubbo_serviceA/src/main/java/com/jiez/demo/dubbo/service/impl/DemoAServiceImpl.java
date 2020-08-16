package com.jiez.demo.dubbo.service.impl;

import com.jiez.demo.dubbo.service.IDemoAService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Service;

/**
 * @author by Jiez
 * @classname DemoAServiceImpl
 * @description TODO
 * @date 2020/6/21 15:54
 */
@Service
public class DemoAServiceImpl implements IDemoAService {

    @Override
    public String sayHi(String name) {
        return "Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }
}
