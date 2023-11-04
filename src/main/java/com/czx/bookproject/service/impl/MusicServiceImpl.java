package com.czx.bookproject.service.impl;

import com.czx.bookproject.Bean.Music;
import com.czx.bookproject.mapper.MusicMapper;
import com.czx.bookproject.service.MusicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    MusicMapper musicMapper;

    @Override
    public List<Music> getAllMusicsInfo() {
        return musicMapper.getAllMusics();
    }

    @Override
    public PageInfo<Music> getUserAllMusics(Integer pageNum) {
        //默认访问的是第一页
        if (pageNum==null){
            pageNum=1;
        }

        //打开分页功能
        PageHelper.startPage(pageNum,5);
        //获取用户音乐数据
        List<Music> userAllMusics = musicMapper.getUserAllMusics();
        //对数据分页
        PageInfo<Music> musicPageInfo = new PageInfo<Music>(userAllMusics);

        return musicPageInfo;
    }

    @Override
    public Music getMusicInfo(Integer id) {
        return musicMapper.getInfoByName(id);
    }

    @Override
    public int updateInfoById(Music music) {
        return musicMapper.updateInfoById(music);
    }

    @Override
    public int deleteInfoById(Integer id) {
        return musicMapper.deleteInfoById(id);
    }

    @Override
    public int insertMusic(Music music) {
        return musicMapper.insertMusic(music);
    }

    @Override
    public List<Music> searchMusicByName(String name) {
        return musicMapper.searchMusicByName(name);
    }
}
