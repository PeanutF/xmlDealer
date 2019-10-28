package com.leadOut.tool;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogCreator {

    RandomAccessFile file;

    public void record(String startTime, String endTime, int totRecord, int[] ids, String type){
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

            for (int id : ids){
                String fileInfo = "ID:" + id + ";"
                        + "       xml文件名：" + id + ".xml" + "\n";
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
