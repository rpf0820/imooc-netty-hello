package com.imooc.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 *  初始化器，channnel注册后，会执行里面的相应初始化方法
 *
 * @author rpf
 * @date 2020/7/16
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel>{

    protected void initChannel(SocketChannel channel) throws Exception {
        // 通过SocketChannel去获取对应的管道
        ChannelPipeline pipeline = channel.pipeline();
        // 通过管道添加handler
        // httpServerCodec是呦netty自己提供的助手类
        // 当请求到服务端，我们需要做解码，响应到客户端做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());

        // 添加自定义的助手类，返回"hello netty"
        pipeline.addLast("customHandler", new CustomHandler());
    }
}
