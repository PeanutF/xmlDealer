package com.leadOut.impl;

import com.leadOut.dao.PhotoFileDao;
import com.leadOut.entity.PhotoFile;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

public class PhotoFileDaoImpl implements PhotoFileDao {

    public SqlSession sqlSession;

    public PhotoFileDaoImpl(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

    @Override
    public List<PhotoFile> queryAll(){
        return this.sqlSession.selectList("PhotoFileDao.queryAll");
    }

    @Override
    public List<PhotoFile> queryById(int id){
        return this.sqlSession.selectList("PhotoFileDao.queryByPhotoId",id);
    }

    @Override
    public List<PhotoFile> queryByTime(String startTimeS, String endTimeS){
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("startTimeS",startTimeS);
        map.put("endTimeS",endTimeS);
        return this.sqlSession.selectList("PhotoFileDao.queryByTime",map);
    }

}
