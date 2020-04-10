package com.shusaku.study.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * @program: rocket-mq
 * @description:
 * @author: Shusaku
 * @create: 2020-04-10 10:45
 */
public class HelloServiceServer {

    public static void main(String[] args) {
        try {

            System.out.println("服务端开启......");
            //创建TProcessor
            TProcessor tProcessor = new thrift.Hello.Processor<thrift.Hello.Iface>(new HelloServiceImpl());
            //创建TserverTransport
            TServerTransport tServerTransport = new TServerSocket(9898);
            //创建TProtocol
            TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();

            TServer.Args tArgs = new TServer.Args(tServerTransport);
            tArgs.processor(tProcessor);
            tArgs.protocolFactory(factory);

            //创建TServer 传入需要的参数　server将以上的参数合并到一起
            TServer tServer = new TSimpleServer(tArgs);
            //启动server
            tServer.serve();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
