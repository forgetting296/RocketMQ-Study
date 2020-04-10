package com.shusaku.study.thrift;

/**
 * @program: rocket-mq
 * @description:
 *                  RPC框架调用基本模式: 首先客户端先序列化调用数据，传给服务端，服务端反序列化提取调用信息，查询客户端所需要的数据，完成之后在序列化返回给客户端，客户端再反序列化得到结果
 *                  Thrift是一个可伸缩的　并且跨语言的一种服务性的开发，它完成的功能实际上是和protobuf类似的　简单来说就是Facebook公布的一款开源的跨语言的RPC框架
 *                  Thrift通过一个中间语言IDL(接口定义语言)来定义RPC的数据类型和接口　这些内容写在以.thrift结尾的文件中 然后通过特殊的编译器来生成不同语言的代码
 * @author: Shusaku
 * @create: 2020-04-10 10:09
 */
public class ThriftStudy {



}
