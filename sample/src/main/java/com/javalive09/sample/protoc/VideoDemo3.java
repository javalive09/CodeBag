package com.javalive09.sample.protoc;

import com.google.protobuf.Any;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

import static com.javalive09.sample.protoc.file.VideoItem3.*;

/**
 * Created by peter on 2017/6/21.
 */

public class VideoDemo3 extends Entry {

    public void writeVideoInfo() throws Exception {
        VideoBean3.Builder videoBuilder = VideoBean3.newBuilder();
        videoBuilder.setVid("SEFFDDF_==DLDLD");
        videoBuilder.setPic("http://7xoxmg.com1.z0.glb.clouddn.com/17k.png");
        videoBuilder.setPayType(1);
        videoBuilder.setTitle("天龙八部");

        //repeated
        videoBuilder.addEpisode("1");
        videoBuilder.addEpisode("2");
        videoBuilder.addEpisode("3");

        //ext
        ExtendInfo.Builder extInfo = ExtendInfo.newBuilder();
        extInfo.setInfo("ext");

        //oneof
        extInfo.setType("type");
        extInfo.setSubTitle("subTitle");

        //maps
        Project p1 = Project.newBuilder().setName("dddd").build();
        Project p2 = Project.newBuilder().setName("eeee").build();

        extInfo.putProjects("a", p1);
        extInfo.putProjects("a", p2);

        videoBuilder.setDetails(Any.pack(extInfo.build()));

        FileOutputStream output = new FileOutputStream(getFile(fileName));
        videoBuilder.build().writeTo(output);
        output.close();
    }

    public void readVideoInfo() throws Exception {
        FileInputStream input = new FileInputStream(getFile(fileName));
        VideoBean3 receiveBaseData = VideoBean3.parseFrom(input);

        ExtendInfo msg = receiveBaseData.getDetails().unpack(ExtendInfo.class);

        Log.i("pay_type = " + receiveBaseData.getPayType());
        Log.i("pic = " + receiveBaseData.getPic());
        Log.i("title = " + receiveBaseData.getTitle());
        Log.i("vid = " + receiveBaseData.getVid());

        //episode
        Log.i("episode = " + receiveBaseData.getEpisode(0));
        Log.i("episode = " + receiveBaseData.getEpisode(1));
        Log.i("episode = " + receiveBaseData.getEpisode(2));

        //ext
        Log.i("info = " + msg.getInfo());

        //oneof
        Log.i("typ = " + msg.getType());
        Log.i("subtitle = " + msg.getSubTitle());

        //maps
        Map<String , Project> map = msg.getProjectsMap();
        Log.i("map", map.toString());

        input.close();
    }

    String fileName = "proto.txt";

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

}
