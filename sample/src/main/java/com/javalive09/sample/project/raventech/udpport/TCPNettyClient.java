package com.javalive09.sample.project.raventech.udpport;

import android.text.TextUtils;


import java.io.IOException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 用netty 实现的TCP client
 * <p>
 * Created by peter on 2016/10/20.
 */

public class TCPNettyClient {

    private EventLoopGroup group;
    private Bootstrap bootstrap;
    private Channel channel;
    private Box box;


    public TCPNettyClient( Box box) {
        this.box = box;
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
//                .option(ChannelOption.SO_REUSEADDR, true)
                .handler(new TCPNettyClientHandler( box, TCPNettyClient.this));
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String host = box.ip;
                    int port = box.port;
                    ChannelFuture f = bootstrap.connect(host, port).sync();
                    // Start the client.
                    channel = f.channel();
                    // Wait until the connection is closed.
                    channel.closeFuture().sync();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    stop();
                }
            }
        }).start();
    }

    public Box getBox() {
        return box;
    }

    public boolean isConnected() {
        return channel.isOpen();
    }

    public void sendTo(String json) throws IOException {
        if (!TextUtils.isEmpty(json)) {
            if (channel != null) {
                channel.writeAndFlush(json);
            }
        }
    }

    public void stop() {
        group.shutdownGracefully();
    }

}
