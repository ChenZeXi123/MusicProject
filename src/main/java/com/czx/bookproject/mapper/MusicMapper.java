package com.czx.bookproject.mapper;

import com.czx.bookproject.Bean.Music;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MusicMapper {

    //获取音乐所有数据
    @Select("select * from t_music ")
    List<Music> getAllMusics();

    //获取用户个人音乐信息
    @Select("select * from t_music ")
    List<Music> getUserAllMusics();

    //通过音乐名获取音乐信息
    @Select("select * from t_music where id=#{id}")
    Music getInfoByName(@Param("id") Integer id);

    //通过音乐名修改音乐
    @Update("update t_music set name=#{music.name},author=#{music.author}," +
            "imgPath=#{music.imgPath},audioPath=#{music.audioPath} where id=#{music.id}")
    int updateInfoById(@Param("music")Music music);

    //通过音乐名删除音乐
    @Delete("delete from t_music where id=#{id}")
    int deleteInfoById(@Param("id") Integer id);

    //添加音乐
    @Insert("insert into t_music (name,author,imgPath,audioPath) values(#{music.name},#{music.author},#{music.imgPath},#{music.audioPath})")
    int insertMusic(@Param("music")Music music);

    //通过名称搜索音乐
    @Select("select * from t_music where name like concat('%',#{nameOrAuthor},'%') or author like concat('%',#{nameOrAuthor},'%')")
    List<Music> searchMusicByName(@Param("nameOrAuthor") String nameOrAuthor);
}
