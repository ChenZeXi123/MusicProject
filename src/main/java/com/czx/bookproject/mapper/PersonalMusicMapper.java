package com.czx.bookproject.mapper;

import com.czx.bookproject.Bean.Music;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PersonalMusicMapper {
    //查询用户的所有音乐信息
    @Select("select * from t_personalmusic")
    public List<Music> getAllMusic();

    //添加指定音乐到该用户中
    @Insert("insert into t_personalmusic (name,author,imgPath,audioPath) values(#{music.name},#{music.author},#{music.imgPath},#{music.audioPath})")
    public int insertMusic(@Param("music")Music music);

    //删除音乐byid
    @Delete("delete from t_personalmusic where id=#{id}")
    public int deleteMusicById(@Param("id") Integer id);

}
