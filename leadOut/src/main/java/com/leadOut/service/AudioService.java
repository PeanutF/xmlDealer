package com.leadOut.service;

import com.leadOut.Layout;
import com.leadOut.dao.AudioFileDao;
import com.leadOut.entity.AudioFile;
import com.leadOut.entity.PhotoFile;
import com.leadOut.impl.AudioFileDaoImpl;
import com.leadOut.tool.GUID;
import com.leadOut.tool.LogCreator;
import com.leadOut.tool.XMLDealer;
import oracle.jdbc.driver.HAManager;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
            audioFile.setGuid(new GUID().toString().replace("-",""));
            dealer.createAudioXMLFile(audioFile,fileName,tot);
        }

        if (audioFiles.size() == 0){
            Layout.empty = true;
        }else {

            HashMap<String,String> GUIDMap = new HashMap<String, String>();

            for (AudioFile audioFile : audioFiles){
                GUIDMap.put(String.valueOf(audioFile.getId()),audioFile.getGuid());
            }

            new LogCreator().record(startTimeS,endTimeS,audioFiles.size(),GUIDMap,"视频");
        }
    }
}
