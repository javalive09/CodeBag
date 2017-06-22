package com.javalive09.sample.protoc;

import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by peter on 2017/6/21.
 */

public class Runner extends Entry {

    String fileName = "proto.txt";
    String fileName_ext = "proto_ext.txt";
    String fileName_convert = "proto_convert.txt";

    /**
     * /storage/emulated/0/Android/data/com.javalive09.sample/cache
     *
     * @param fileName
     * @return
     */
    private File getFile(String fileName) {
        File dir = getActivity().getExternalCacheDir();
        File file = new File(dir, fileName);
        return file;
    }

    public void writeVideoInfo() throws Exception {
        VideoItem.VideoBean.Builder videoBuilder = VideoItem.VideoBean.newBuilder();
        videoBuilder.setVid("SEFFDDF_==DLDLD");
        videoBuilder.setPic("http://7xoxmg.com1.z0.glb.clouddn.com/17k.png");
        videoBuilder.setPayType(1);
        videoBuilder.setTitle("天龙八部");

        VideoItem.VideoBean videoBean = videoBuilder.build();

        FileOutputStream output = new FileOutputStream(getFile(fileName));
        videoBean.writeTo(output);
        output.close();
    }

    public void readVideoInfo() throws Exception {
        FileInputStream input = new FileInputStream(getFile(fileName));
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        VideoItem.VideoBean receiveBaseData = VideoItem.VideoBean.parseFrom(input, registry);
        Log.i("pay_type = " + receiveBaseData.getPayType());
        Log.i("pic = " + receiveBaseData.getPic());
        Log.i("title = " + receiveBaseData.getTitle());
        Log.i("vid = " + receiveBaseData.getVid());
        input.close();
    }

    public void writeVideoInfo_ext() throws Exception {
        VideoItem.VideoBean.Builder videoBuilder = VideoItem.VideoBean.newBuilder();
        videoBuilder.setVid("SEFFDDF_==DLDLD");
        videoBuilder.setPic("http://7xoxmg.com1.z0.glb.clouddn.com/17k.png");
        videoBuilder.setPayType(1);
        videoBuilder.setTitle("天龙八部");

        VideoItem.ExtendInfo.Builder extInfoBuilder = VideoItem.ExtendInfo.newBuilder();
        extInfoBuilder.setDes("des");
        extInfoBuilder.setSubTitle("subtitle");
        extInfoBuilder.setOther("other");
        videoBuilder.setExtension(VideoItem.ExtendInfo.info, extInfoBuilder.build());

        VideoItem.VideoBean videoBean = videoBuilder.build();

        FileOutputStream output = new FileOutputStream(getFile(fileName));
        videoBean.writeTo(output);
        output.close();

        transferVideoInfo_ext();
    }

    private void transferVideoInfo_ext() throws Exception {
        FileInputStream input = new FileInputStream(getFile(fileName));
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(VideoItem.ExtendInfo.info);
        VideoItem.VideoBean receiveBaseData = VideoItem.VideoBean.parseFrom(input, registry);

        VideoItem.VideoBean.Builder videoBuilder = VideoItem.VideoBean.newBuilder();
        videoBuilder.setPayType(receiveBaseData.getPayType());
        videoBuilder.setPic(receiveBaseData.getPic());
        videoBuilder.setTitle(receiveBaseData.getTitle());
        videoBuilder.setVid(receiveBaseData.getVid());

        videoBuilder.setExtension(VideoItem.ExtendInfo.info, receiveBaseData.getExtension(VideoItem.ExtendInfo.info));

        VideoItem.VideoBean videoBean = videoBuilder.build();

        FileOutputStream output = new FileOutputStream(getFile(fileName_ext));
        videoBean.writeTo(output);
        input.close();
        output.close();
    }

    public void readVideoInfo_ext() throws Exception {
        FileInputStream input = new FileInputStream(getFile(fileName_ext));

        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(VideoItem.ExtendInfo.info);
        VideoItem.VideoBean receiveBaseData = VideoItem.VideoBean.parseFrom(input, registry);

        Log.i("pay_type = " + receiveBaseData.getPayType());
        Log.i("pic = " + receiveBaseData.getPic());
        Log.i("title = " + receiveBaseData.getTitle());
        Log.i("vid = " + receiveBaseData.getVid());
        Log.i("ext subtitle = " + receiveBaseData.getExtension(VideoItem.ExtendInfo.info).getSubTitle());
        Log.i("ext des = " + receiveBaseData.getExtension(VideoItem.ExtendInfo.info).getDes());
        Log.i("ext other = " + receiveBaseData.getExtension(VideoItem.ExtendInfo.info).getOther());
        input.close();
    }

    public void Proto2Json() throws Exception {
        JsonToProto.VideoBean.Builder videoBuilder = JsonToProto.VideoBean.newBuilder();
        videoBuilder.setVid("SEFFDDF_==DLDLD");
        videoBuilder.setPic("http://7xoxmg.com1.z0.glb.clouddn.com/17k.png");
        videoBuilder.setPayType(1);
        videoBuilder.setTitle("天龙八部");
        Message message = videoBuilder.build();
        String jsonFormat = new JsonFormat().printToString(message);
        Log.i(jsonFormat);

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(getFile(fileName_convert)));
        outputStreamWriter.write(jsonFormat);
        outputStreamWriter.close();

    }

    public void Json2Proto() throws Exception {

        JsonToProto.VideoBean.Builder videoBuilder = JsonToProto.VideoBean.newBuilder();
        FileInputStream input = new FileInputStream(getFile(fileName_convert));

        new JsonFormat().merge(input, videoBuilder);

        Log.i("pay_type = " + videoBuilder.getPayType());
        Log.i("pic = " + videoBuilder.getPic());
        Log.i("title = " + videoBuilder.getTitle());
        Log.i("vid = " + videoBuilder.getVid());

    }

}
