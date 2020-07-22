package com.imooc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *  实现客户端发送一个请求，服务端返回hello netty
 *
 * @author rpf
 * @date 2020/7/16
 */
public class HelloServer {

    public static void main(String[] args) throws Exception {

        // 定义一对线程组
        // 主线程组  用于接收客户端的连接，但是不做任何处理
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        // 从线程组  主线程组会把任务丢过来
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // netty服务器的创建  ServerBootstrap是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workerGroup)  // 设置主从线城组
                    .channel(NioServerSocketChannel.class) // 设置nio双向通道
                    .childHandler(null); // 子处理器,用于处理workerGroup

            // 启动server,并且设置8088为启动端口号，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();

            // 监听关闭的channel，设置同步方式
            channelFuture.channel().close().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
