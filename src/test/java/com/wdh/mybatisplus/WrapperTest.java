package com.wdh.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wdh.mybatisplus.mapper.UserMapper;
import com.wdh.mybatisplus.pojo.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@DisplayName("WrapperTest")
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void selectWrapper() {
        // 查询姓名以”用户“结尾，年龄在20到30岁，email不为null的用户
        //  SELECT id,name,age,email,is_deleted
        //  FROM t_user WHERE is_deleted=0 AND (name LIKE ? AND email IS NOT NULL AND age BETWEEN ? AND ?)
        //   Parameters : %用户(String), 20(String), 30(Integer)
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.likeLeft("name","用户")
                        .isNotNull("email")
                        .between("age","20",30);

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }
    
    @Test 
    void orderBy(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        // Preparing: SELECT id,name,age,email,is_deleted FROM t_user
        // WHERE is_deleted=0 ORDER BY age DESC,id ASC
        userQueryWrapper.orderByDesc("age")
                        .orderByAsc("id");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void delete(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.isNull("email");
        userMapper.delete(userQueryWrapper);
    }
}
