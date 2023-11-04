package com.czx.bookproject.mapper;

import com.czx.bookproject.Bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from t_user where username=#{username} && password=#{password}")
    User selectUserByUsernameAndPassword (@Param("username") String username, @Param("password")String password);

    @Select("select username from t_user where username=#{username}")
    String selectUsername(String username);

    @Insert("insert into t_user (username,password,email) values(#{username},#{password},#{email})")
    int insertUser(String username,String password,String email);
}
