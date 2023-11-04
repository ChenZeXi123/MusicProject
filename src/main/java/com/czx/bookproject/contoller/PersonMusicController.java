package com.czx.bookproject.contoller;

import com.czx.bookproject.Bean.Music;
import com.czx.bookproject.service.PersonalMusicService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class PersonMusicController {
    @Autowired
    PersonalMusicService personalMusicService;

    //实现分页功能,点击页码得到对应页码的数据
    @ResponseBody
    @GetMapping("/getPersonalMusics")
    public PageInfo<Music> getPersonalMusics(@RequestParam("pageNum") Integer pageNum){
        return personalMusicService.getAllMusicsByPageNum(pageNum);
    }

    //添加音乐到用户中
    @ResponseBody
    @PostMapping("/insertMusic")
    public String insertMusic(@RequestBody Music music, HttpSession session) throws ServletException, IOException {
        //用户在音乐首页点击添加音乐到个人时,先判断是否已经登录
        if (session.getAttribute("user")==null){
            return "添加失败,请先登录噢!";
        }

        //service返回字符串,通知个人音乐中是否已经存在相同音乐
        String isExist = personalMusicService.insertMusic(music);
        return isExist;
    }

    //返回到登录页面
    @GetMapping ("/returnLogin")
    public String returnLogin(){
        return "login";
    }

    //通过id删除音乐
    @DeleteMapping("/deleteMusicById")
    public String deleteMusicById(@RequestParam("id") Integer id){
        System.out.println("音乐id为："+id);
        personalMusicService.deleteMusicById(id);
        return "personalMusic";
    }
}
