package com.leadOut.dao;

import com.leadOut.entity.PhotoFile;
import com.leadOut.impl.PhotoFileDaoImpl;
import com.leadOut.tool.XMLDealer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class PhotoFileDaoTest {

    public PhotoFileDao photoFileDao;
    public SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        this.photoFileDao = new PhotoFileDaoImpl(sqlSession);


    }

    @Test
    public void queryAll() {
        List<PhotoFile> photoFiles = photoFileDao.queryAll();
        for (PhotoFile t :
                photoFiles) {
            System.out.println(t.getCreateUser_());
        }
        XMLDealer dealer = new XMLDealer();
//        dealer.createPhotoXMLFile(photoFiles,new File("D:\\dom4j.xml"));
    }

    @Test
    public void queryById(){
        List<PhotoFile> photoFiles = photoFileDao.queryById(94167);
        for (PhotoFile t :
                photoFiles) {
            System.out.println(t.getCreateUser_());
        }
        XMLDealer dealer = new XMLDealer();
    }
}