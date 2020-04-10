package com.shusaku.study.thrift;

import org.apache.thrift.TException;

/**
 * @program: rocket-mq
 * @description:
 * @author: Shusaku
 * @create: 2020-04-10 10:43
 */
public class HelloServiceImpl implements thrift.Hello.Iface {

    @Override
    public String helloString(String para) throws TException {
        return "result: " + para;
    }
}
