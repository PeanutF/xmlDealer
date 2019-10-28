package com.leadOut.dao;

import com.leadOut.entity.AudioFile;
import com.leadOut.entity.PhotoFile;

import java.util.List;

public interface AudioFileDao {
    List<AudioFile> queryByTime(String startTimeS, String endTimeS);
}
