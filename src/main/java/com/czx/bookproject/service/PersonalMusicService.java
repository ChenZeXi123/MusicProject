package com.czx.bookproject.service;

import com.czx.bookproject.Bean.Music;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonalMusicService {
    //通过当前页获取用户音乐信息
    PageInfo<Music> getAllMusicsByPageNum(Integer page);

    //添加音乐到用户中
    public String insertMusic(Music music);

    //删除音乐
    public int deleteMusicById(Integer id);

    //获取所有音乐信息
    List<Music> getAllMusic();
}
