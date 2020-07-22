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
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
    }
}
