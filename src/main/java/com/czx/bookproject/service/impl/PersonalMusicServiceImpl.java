package com.czx.bookproject.service.impl;

import com.czx.bookproject.Bean.Music;
import com.czx.bookproject.mapper.PersonalMusicMapper;
import com.czx.bookproject.service.PersonalMusicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalMusicServiceImpl implements PersonalMusicService {
    @Autowired
    PersonalMusicMapper personalMusicMapper;

    @Override
    public PageInfo<Music> getAllMusicsByPageNum(Integer page) {
        //开启分页功能(默认从第一页开始)
        PageHelper.startPage(page,4);
        //获取个人用户所有音乐信息
        List<Music> musics = personalMusicMapper.getAllMusic();
        //对音乐信息进行分页
        PageInfo<Music> musicPageInfo=new PageInfo<>(musics);

        return musicPageInfo;

    }

    @Override
    public String insertMusic(Music music) {
        //先获取用户个人音乐信息,如果已经有该音乐则不再添加
        List<Music> allMusic = personalMusicMapper.getAllMusic();

        //用于判断个人音乐中是否已经存在相同音乐
        boolean isExist=false;

        for (Music item :allMusic){
            if (item.getAuthor().equals(music.getAuthor()) && item.getName().equals(music.getName()) ){
                isExist=true;
                break;
            }
        }
        //判断是否已经存在该音乐
        if (isExist){
            System.out.println("======添加失败");
            return "添加失败,已存在该音乐了噢！";
        }else {
            System.out.println("======添加成功");
            personalMusicMapper.insertMusic(music);
            return null;
        }
    }

    @Override
    public int deleteMusicById(Integer id) {
        return personalMusicMapper.deleteMusicById(id);
    }

    @Override
    public List<Music> getAllMusic() {
        return personalMusicMapper.getAllMusic();
    }
}
