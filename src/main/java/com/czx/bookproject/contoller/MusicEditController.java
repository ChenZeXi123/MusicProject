package com.czx.bookproject.contoller;

import com.czx.bookproject.Bean.Music;
import com.czx.bookproject.mapper.MusicMapper;
import com.czx.bookproject.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MusicEditController {
    @Autowired
    MusicService musicService;

    @GetMapping("/getInfoById")
    public String getInfoByName(Integer id, Model model){
        Music music = musicService.getMusicInfo(id);
       model.addAttribute("music",music);
        return "manager/musicEdit";
    }

    //修改音乐信息,并且返回音乐编辑页面
    @PostMapping("/updateMusicInfoById")
    public String updateMusicInfoById( Music music,
                                       @RequestParam("imgPathByFile") MultipartFile imgPathFile,
                                       @RequestParam("audioPathByFile")MultipartFile audioPathByFile) throws IOException {

        //先判断修改文件是通过上传文件,还是仅仅只是修改名字。
        //如果是通过上传文件,则需要将文件上传到target目录下
        if (!imgPathFile.isEmpty()){
            String imgPathFileName = imgPathFile.getOriginalFilename();

            //截取文件名,只要后缀名  (在截取时,会包含前面的,而不包含后面的)
            String imgSufName = imgPathFileName.substring(imgPathFileName.lastIndexOf("."));

            //使用UUID获取随机数,让文件名改为随机数,这样重复上传同个文件时,就不会覆盖之前的文件了
            String randNum = UUID.randomUUID().toString();
            System.out.println(randNum);
            String imgRandNum=randNum.substring(randNum.length()/2);
            System.out.println(imgRandNum);

            //将随机数和后缀名进行拼接,得到新的文件名
            String noRepeatImgFilename = imgRandNum + imgSufName;

            //指明要上传的位置
            String imgUploadLocation="D:\\springBoot2\\bookProject\\target\\classes\\static\\music_img\\"+noRepeatImgFilename;
            System.out.println(imgUploadLocation);

            //将文件上传到指定路径
            imgPathFile.transferTo(new File(imgUploadLocation));

            //获取到这张图片的资源的相对路径
            String imgPath="../music_img/"+noRepeatImgFilename;

            //将此相对路径封装到music对象中,用于修改
            music.setImgPath(imgPath);
        }

        if (!audioPathByFile.isEmpty()){
            String audioFileName = audioPathByFile.getOriginalFilename();
            System.out.println("文件名为:"+audioFileName);

            //截取文件名,只要后缀名  (在截取时,会包含前面的,而不包含后面的)
            String audioSufName = audioFileName.substring(audioFileName.lastIndexOf("."));

            //使用UUID获取随机数,让文件名改为随机数,这样重复上传同个文件时,就不会覆盖之前的文件了
            String audioRandNum = UUID.randomUUID().toString();
            String halfAudioRandNum=audioRandNum.substring(audioRandNum.length()/2);

            //将随机数和后缀名进行拼接,得到新的文件名
            String noRepeatAudioFilename = audioRandNum + audioSufName;

            //指明要上传的位置
            String audioUploadLocation="D:\\springBoot2\\bookProject\\target\\classes\\static\\music_audio\\"+noRepeatAudioFilename;
            System.out.println(audioUploadLocation);

            //将文件上传到指定路径
            audioPathByFile.transferTo(new File(audioUploadLocation));

            //获取到这张图片的资源的相对路径
            String audioPath="../music_audio/"+noRepeatAudioFilename;

            //将此相对路径封装到music对象中,用于修改
            music.setAudioPath(audioPath);
        }

         musicService.updateInfoById(music);
        return "manager/musicMange";
    }

    //删除音乐信息,并且返回音乐编辑页面
    @GetMapping("/deleteInfoById")
    public String deleteInfoById(Integer musicId){
        int row = musicService.deleteInfoById(musicId);
        return "manager/musicMange";
    }

    //用于在编辑页面点击提交时,判断当前是添加操作
    @RequestMapping("/judgeAdd")
    public String addMusic(Model model) {
        model.addAttribute("isAdd","add");
        return "manager/musicEdit";
    }

    //添加音乐
    @PostMapping("/addMusic")
    public String addMusic(Music music,
                           @RequestParam("imgPathByFile") MultipartFile imgPathFile,
                           @RequestParam("audioPathByFile")MultipartFile audioPathByFile) throws IOException {
        //1.先实现音频的上传
        //获取文件名
        String audioFileName = audioPathByFile.getOriginalFilename();

        //截取文件名,只要后缀名  (在截取时,会包含前面的,而不包含后面的)
        String audioSufName = audioFileName.substring(audioFileName.lastIndexOf("."));

        //使用UUID获取随机数,让文件名改为随机数,这样重复上传同个文件时,就不会覆盖之前的文件了
        String randNum = UUID.randomUUID().toString();
        String audioRandNum=randNum.substring(randNum.length()/2);

        //将随机数和后缀名进行拼接,得到新的文件名
        String noRepeatAudioFilename = audioRandNum + audioSufName;

        //指明要上传的位置
        String audioUploadLocation="D:\\springBoot2\\bookProject\\target\\classes\\static\\music_audio\\"+noRepeatAudioFilename;

        //将文件上传到指定文件
        audioPathByFile.transferTo(new File(audioUploadLocation));

//-------------------------------------------------------------------

        //2.再实现图片的上传

        String imgFileName = imgPathFile.getOriginalFilename();

        //截取文件名,只要后缀名  (在截取时,会包含前面的,而不包含后面的)
        String imgSufName = imgFileName.substring(imgFileName.lastIndexOf("."));

        //使用UUID获取随机数,让文件名改为随机数,这样重复上传同个文件时,就不会覆盖之前的文件了
        String randNum2 = UUID.randomUUID().toString();
        String imgRandNum = randNum2.substring(randNum2.length() / 2);

        //将随机数和后缀名进行拼接,得到新的文件名
        String noRepeatImgFilename = imgRandNum + imgSufName;

        //指明要上传的位置
        String imgUploadLocation="D:\\springBoot2\\bookProject\\target\\classes\\static\\music_img\\"+noRepeatImgFilename;

        //将文件上传到指定文件
        imgPathFile.transferTo(new File(imgUploadLocation));


        //将音频地址和图片地址信息封装到music对象中
        //需要将音频的地址和图片地址修改为在index.html页面中获取资源时的地址路径
        String audioPath="../music_audio/"+noRepeatAudioFilename;
        String imgPath="../music_img/"+noRepeatImgFilename;

        music.setAudioPath(audioPath);
        music.setImgPath(imgPath);

        musicService.insertMusic(music);
        return "manager/musicMange";
    }

}
