package com.leadOut.tool;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LogCreator {

    RandomAccessFile file;

    public void record(String startTime, String endTime, int totRecord, HashMap<String,String> GUIDMap, String type){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String time = dateFormat.format(new Date());

            file = new RandomAccessFile(Config.logPath + "\\"+ time +".txt ","rw");
            String info = "开始时间:" + startTime + ";"
                    + " 结束时间:" + endTime + ";"
                    + " 总记录条数:" + totRecord + ";"
                    + " 导出日期:" + new Date().toString() + ";"
                    + " 类型: " + type + ";"
                    + " \n";

            StringBuffer buffer = new StringBuffer();
            buffer.append(info);

            for (String key:GUIDMap.keySet()){
                String fileInfo = "ID:" + key + ";"
                        + "       xml文件名：" + key + ".xml" + "; GUID:" + GUIDMap.get(key) + "\n";
                buffer.append(fileInfo);
            }
            try {
                file.seek(file.length());
                file.write(buffer.toString().getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
