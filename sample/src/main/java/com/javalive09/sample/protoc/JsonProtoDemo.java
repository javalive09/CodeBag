package com.javalive09.sample.protoc;

import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;
import com.javalive09.sample.protoc.file.JsonToProto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by peter on 2017/6/21.
 */

public class JsonProtoDemo extends Entry {

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

}
