package com.leadOut.impl;

import com.leadOut.dao.AudioFileDao;
import com.leadOut.entity.AudioFile;
import com.leadOut.entity.PhotoFile;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

public class AudioFileDaoImpl implements AudioFileDao {

    public SqlSession sqlSession;

    public AudioFileDaoImpl(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }


    @Override
    public List<AudioFile> queryByTime(String startTimeS, String endTimeS){
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("startTimeS",startTimeS);
        map.put("endTimeS",endTimeS);
        return this.sqlSession.selectList("AudioFileDao.queryByTime",map);
    }

}
