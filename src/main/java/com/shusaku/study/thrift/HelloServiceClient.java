package com.shusaku.study.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.Hello;

/**
 * @program: rocket-mq
 * @description:
 * @author: Shusaku
 * @create: 2020-04-10 10:45
 */
public class HelloServiceClient {

    public static void main(String[] args) {

        System.out.println("客户端启动....");
        TTransport tTransport = null;
        try {

            tTransport = new TSocket("localhost", 9898, 30000);
            //协议要和服务端一致
            TProtocol tProtocol = new TBinaryProtocol(tTransport);
            Hello.Client client = new Hello.Client(tProtocol);

            tTransport.open();
            String result = client.helloString("hello thrift");
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != tTransport) {
                tTransport.close();
            }
        }
    }

}
