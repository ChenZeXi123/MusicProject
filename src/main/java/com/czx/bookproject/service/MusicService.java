package com.czx.bookproject.service;

import com.czx.bookproject.Bean.Music;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MusicService {

    //获取所有的音乐数据
    List<Music> getAllMusicsInfo();

    //获取用户个人音乐数据
    PageInfo<Music> getUserAllMusics(Integer pageNum);

    //获取单个音乐信息
    Music getMusicInfo(Integer id);

    //修改音乐信息
    int updateInfoById(Music music);

    //删除音乐
    int deleteInfoById(Integer id);

    //添加音乐
    int insertMusic(Music music);

    //搜索音乐
    List<Music> searchMusicByName(String name);
}
