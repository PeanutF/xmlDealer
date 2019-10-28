package com.leadOut.service;

import com.leadOut.Layout;
import com.leadOut.dao.AudioFileDao;
import com.leadOut.entity.AudioFile;
import com.leadOut.impl.AudioFileDaoImpl;
import com.leadOut.tool.LogCreator;
import com.leadOut.tool.XMLDealer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AudioService {

    SqlSession sqlSession;
    AudioFileDao audioFileDao;

    public AudioService(){
        init();
    }


    public void init(){

        String resource = "mybatis-config.xml";
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        this.audioFileDao = new AudioFileDaoImpl(sqlSession);
    }

    public void createXMLByTime(String startTimeS,String endTimeS,String path){
        Layout.process = 0;
        List<AudioFile> audioFiles = audioFileDao.queryByTime(startTimeS,endTimeS);
        int tot = 0;

        XMLDealer dealer = new XMLDealer();

        for (AudioFile audioFile : audioFiles){
            String fileName = path + "\\" + audioFile.getId()+".xml";
            tot++;
            Layout.process = tot * 100 / audioFiles.size();
            dealer.createAudioXMLFile(audioFile,new File(fileName));
        }

        if (audioFiles.size() == 0){
            Layout.empty = true;
        }else {

            int[] ids = new int[audioFiles.size()];
            int sub = 0;
            for (AudioFile audioFile : audioFiles){
                ids[sub] = audioFile.getId();
                sub ++;
            }

            new LogCreator().record(startTimeS,endTimeS,audioFiles.size(),ids,"视频");
        }
    }
}
