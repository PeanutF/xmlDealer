package com.leadOut.dao;

import com.leadOut.entity.PhotoFile;

import java.util.List;

public interface PhotoFileDao {

    List<PhotoFile> queryAll();
    List<PhotoFile> queryById(int id);
    List<PhotoFile> queryByTime(String startTimeS, String endTimeS);
}
