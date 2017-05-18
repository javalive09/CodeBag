package com.javalive09.sample.project.raventech.udpport;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 *
 * 用netty 实现的udp receiver 用于处理box 发送的信息。
 *
 * Created by peter on 2016/10/17.
 *
 */

public class UDPNettyReceiver {

    private static final int UDP_PORT = 1980;
    private final EventLoopGroup group;
    private final Bootstrap bootstrap;
    private UDPNettyReceiverHandler receiverHandler;

    public UDPNettyReceiver() {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        receiverHandler = new UDPNettyReceiverHandler();
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
//                .option(ChannelOption.SO_REUSEADDR, true)
                .handler(receiverHandler);
    }

    public void start() {
        new Thread((new Runnable() {
            @Override
            public void run() {
                try {
                    bootstrap.bind(UDP_PORT).sync().channel().closeFuture().await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    stop();
                }
            }
        })).start();
    }

    public void stop() {
        group.shutdownGracefully();
    }

    public void setListener(UDPListener listener) {
        receiverHandler.setListener(listener);
    }

    public void removeListener() {
        if(receiverHandler != null) {
            receiverHandler.setListener(null);
        }
    }

    public interface UDPListener {
        void startConnectTimer(String boxUUID);
    }
}

