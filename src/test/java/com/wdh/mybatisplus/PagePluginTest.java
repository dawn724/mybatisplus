package com.wdh.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdh.mybatisplus.mapper.UserMapper;
import com.wdh.mybatisplus.pojo.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@DisplayName("PagePluginTest")
public class PagePluginTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void pageTest() {
        // select * from t_user where 逻辑删除判断 limit index,pageSize
        // index = (当前页码-1) * pageSize
        // SELECT COUNT(*) AS total FROM t_user WHERE is_deleted = 0
        Page<User> page = new Page<>(2,3);
        Page<User> userPage = userMapper.selectPage(page, null);
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    void pageVo() {
        Page<User> page = new Page<>(3, 3);
        Page<User> userPage = userMapper.selectPageVo(page, 20);
        System.out.println(userPage.getPages());
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
    }
}
