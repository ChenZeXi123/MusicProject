package com.czx.bookproject.contoller;

import com.czx.bookproject.Bean.Music;
import com.czx.bookproject.service.MusicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class MusicController {
    @Autowired
    MusicService musicService;

    //指定要访问数据库中的第几页
    @GetMapping("/getMusicByPageNum")
    public PageInfo<Music> getmusicByPage(Integer pageNum){
        //开启分页功能 (参数一:从第几页开始查询,首页是1  参数二:每页显示几条数据)
        PageHelper.startPage(pageNum,4);

        //获取数据库中的数据信息
        List<Music> allMusicsInfo = musicService.getAllMusicsInfo();

        //将获取的数据进行分页(参数一：在数据库中查询得到的数据   参数二:分页导航要有几页)
        PageInfo<Music> musicPageInfo = new PageInfo<>(allMusicsInfo,4);
        /*
        此时的musicPageInfo,不仅包含了当前页的数据,还提供了以下属性信息
        list属性包含了数据信息
        pageNum=1, pageSize=2, size=2, startRow=1, endRow=2, total=4, pages=2,
        prePage=0, nextPage=0, isFirstPage=true, isLastPage=true,
        hasPreviousPage=false, hasNextPage=false,navigatePages=4,
        navigateFirstPage=1(此时的分页导航第一个元素是是第几页),
        navigateLastPage=1(此时的分页导航最后一个元素是是第几页),
        navigatepageNums=[1](显示当前分页导航中所有的页码)
      */

        return musicPageInfo;
    }

    //访问首页时,获取数据库中第一页的数据
    @GetMapping("/getAll")
    public PageInfo<Music> getAllMusics(){
        //开启分页功能 (参数一:从第几页开始查询,首页是1  参数二:每页显示几条数据)
        PageHelper.startPage(1,4);

        //获取数据库中的数据信息
        List<Music> allMusicsInfo = musicService.getAllMusicsInfo();

        //将获取的数据进行分页(参数一：在数据库中查询得到的数据   参数二:分页导航要有几页)
        PageInfo<Music> musicPageInfo = new PageInfo<>(allMusicsInfo,4);
        /*
        此时的musicPageInfo,不仅包含了当前页的数据,还提供了以下属性信息
        list属性包含了数据信息
        pageNum=1, pageSize=2, size=2, startRow=1, endRow=2, total=4, pages=2,
        prePage=0, nextPage=0, isFirstPage=true, isLastPage=true,
        hasPreviousPage=false, hasNextPage=false,navigatePages=4,
        navigateFirstPage=1(此时的分页导航第一个元素是是第几页),
        navigateLastPage=1(此时的分页导航最后一个元素是是第几页),
        navigatepageNums=[1](显示当前分页导航页码)
      */

        System.out.println(musicPageInfo);

        return musicPageInfo;
    }

    //获取用户个人的音乐信息
    @GetMapping("/myMusic")
    public PageInfo<Music> getUserMusic(Integer pageNum){
        return musicService.getUserAllMusics(pageNum);
    }

    //通过音乐名搜索音乐信息
    @GetMapping("/getMusicByName")
    public PageInfo<Music> getMusicByName(String nameOrAuthor){
        //开启分页功能,默认显示第一页,显示4个数据
        PageHelper.startPage(1,4);
        //模糊查询获取对应的音乐信息
        List<Music> searchMusicByName = musicService.searchMusicByName(nameOrAuthor);
        PageInfo<Music> musicPageInfo=new PageInfo<>(searchMusicByName);
        return musicPageInfo;
    }

}
