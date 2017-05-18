package com.javalive09.sample.project.raventech.udpport;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * TCP client handler 负责初始化编解码器
 *
 * Created by peter on 2016/10/21.
 */

public class TCPNettyClientHandler extends ChannelInitializer<SocketChannel> {

    private static final int MAX_LENGTH = 1024 * 1024;
    private TCPNettyClient client;
    private Box box;

    public TCPNettyClientHandler( Box box, TCPNettyClient client) {
        this.client = client;
        this.box = box;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();

        //分隔符解码器
        byte[] bts = {-1};
        p.addLast(new DelimiterBasedFrameDecoder(MAX_LENGTH, Unpooled.copiedBuffer(bts)));

        //字符解码器
        p.addLast(new StringDecoder());
        Log.i("initChannel", p.channel().toString());
    }
}
