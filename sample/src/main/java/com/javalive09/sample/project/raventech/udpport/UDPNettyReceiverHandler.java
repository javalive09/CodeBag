package com.javalive09.sample.project.raventech.udpport;


import java.net.InetAddress;
import java.util.regex.Pattern;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

/**
 * UDP 接收到的信息处理类 包括盒子发出的 wifi success 和 connect info 信息
 * Created by peter on 2016/10/21.
 */

public class UDPNettyReceiverHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static String boxUUid = "";
    private UDPNettyReceiver.UDPListener listener;

    public UDPNettyReceiverHandler() {
    }

    public void setListener(UDPNettyReceiver.UDPListener listener) {
        this.listener = listener;
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket datagramPacket) throws Exception {
        if (datagramPacket != null) {
//            FlowLog.i("netty-peter", "datagramPacket = " + datagramPacket);
            InetAddress address = datagramPacket.sender().getAddress();
            if (address != null) {
                String data = datagramPacket.content().toString(CharsetUtil.UTF_8);
//                FlowLog.i("netty-peter data = " + data);

                if (isValidConnectStr(data)) {
                    String[] msg = data.split("\t");
                    String questIp = address.toString().substring(1);
                    int port = Integer.parseInt(msg[3], 10);
                    String boxUUID = msg[1];
                    String boxName = msg[2];

                    Box box = new Box();
                    box.ip = questIp;
                    box.uuid = boxUUID;
                    box.port = port;
                    box.name = boxName;
                    TCPNettyClient client = new TCPNettyClient(box);
                    client.start();

                } else if (isValidWifiSucStr(data)) {
                    String[] msg = data.split("\t");
                    String uuid = msg[2];
                    String code = msg[3];

                }
            }
            datagramPacket.retain();
        }
    }

    // ST\tWIFI_SUCCESS\tboxUUID\tCode  收到wifi成功提示的UDP
    private boolean isValidWifiSucStr(String str) {
        String regex = "^ST\\tWIFI_SUCCESS\\t\\P{Z}*\\t\\P{Z}*$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

    // box\tdevice_id\tdevice_name\tport  收到准备连接TCP提示的UDP
    private boolean isValidConnectStr(String str) {
        String regex = "^box\\t\\P{Z}*\\t[\\p{Z}|\\P{Z}]*\\t\\d*$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

    public static String getBoxUUid() {
        return boxUUid;
    }

    public static void setBoxUUid(String boxUUid) {
        UDPNettyReceiverHandler.boxUUid = boxUUid;
    }
}
