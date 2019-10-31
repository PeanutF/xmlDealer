package com.leadOut.service;

import com.leadOut.Layout;
import com.leadOut.dao.PhotoFileDao;
import com.leadOut.entity.AudioFile;
import com.leadOut.entity.PhotoFile;
import com.leadOut.impl.PhotoFileDaoImpl;
import com.leadOut.tool.GUID;
import com.leadOut.tool.LogCreator;
import com.leadOut.tool.XMLDealer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhotoService {

    SqlSession sqlSession;
    PhotoFileDao photoFileDao;

    public PhotoService(){
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
        this.photoFileDao = new PhotoFileDaoImpl(sqlSession);
//        this.photoDao = new PhotoDaoImpl(sqlSession);
    }

//    public List<Photo> selectAllPhoto(){
//        return photoDao.queryAll();
//    }

    public List<PhotoFile> selectByPhotoId(int id){
        return photoFileDao.queryById(id);
    }

    public List<PhotoFile> selectPhotoByTime(String startTimeS,String endTimeS){return  photoFileDao.queryByTime(startTimeS,endTimeS);}

/*
    public void createAllPhotoXML(){
        List<Photo> photos = selectAllPhoto();


        XMLDealer dealer = new XMLDealer();
        for (Photo p : photos){
            //todo 这个要写在配置文件里面 或者选择路径
            String fileName = "D:\\xmlFile\\" + p.getId()+".xml";
            List<PhotoFile> photoFiles = selectByPhotoId(p.getId());
            dealer.createPhotoXMLFile(p,photoFiles,new File(fileName));
        }
    }*/


    public void createXMLByTime(String startTimeS,String endTimeS,String path){
        Layout.process = 0;
        List<PhotoFile> photoFiles = photoFileDao.queryByTime(startTimeS,endTimeS);
        int tot = 0;

        XMLDealer dealer = new XMLDealer();

        for (PhotoFile photoFile : photoFiles){
            String fileName = path + "\\" + photoFile.getId()+".xml";
            tot++;
            Layout.process = tot * 100 / photoFiles.size();
            photoFile.setGuid(new GUID().toString().replace("-",""));
            dealer.createPhotoXMLFile(photoFile,fileName,tot);
        }

        if (photoFiles.size() == 0){
            Layout.empty = true;
        }else {
            HashMap<String,String> GUIDMap = new HashMap<String, String>();


            for (PhotoFile photoFile : photoFiles){
                GUIDMap.put(String.valueOf(photoFile.getId()),photoFile.getGuid());
            }
            new LogCreator().record(startTimeS,endTimeS,photoFiles.size(),GUIDMap,"图片");
        }
    }

}
