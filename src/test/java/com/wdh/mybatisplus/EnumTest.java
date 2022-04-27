package com.wdh.mybatisplus;


import com.wdh.mybatisplus.enumAttribute.Sex;
import com.wdh.mybatisplus.mapper.UserMapper;
import com.wdh.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnumTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void enumTest() {
        User user = new User();
        user.setName("汪奔");
        user.setAge(22);
        user.setEmail("213@qq.com");
        user.setSex(Sex.MALE);
        user.setId(5L);
        userMapper.updateById(user);
    }
}
