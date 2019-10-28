/*
package com.leadOut.impl;

import com.leadOut.dao.PhotoDao;
import com.leadOut.entity.Photo;
import com.leadOut.entity.PhotoFile;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

public class PhotoDaoImpl implements PhotoDao {

    public SqlSession sqlSession;

    public PhotoDaoImpl(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }


    @Override
    public List<Photo> queryAll(){
        return this.sqlSession.selectList("PhotoDao.queryAll");
    }

    @Override
    public List<Photo> queryByTime(String startTimeS,String endTimeS){
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("startTimeS",startTimeS);
        map.put("endTimeS",endTimeS);

        return this.sqlSession.selectList("PhotoDao.queryByTime",map);}
}
*/
