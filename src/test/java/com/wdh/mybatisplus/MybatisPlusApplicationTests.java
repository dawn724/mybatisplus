package com.wdh.mybatisplus;

import com.wdh.mybatisplus.mapper.UserMapper;
import com.wdh.mybatisplus.pojo.User;
import com.wdh.mybatisplus.service.UserService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@DisplayName("Mapper测试")
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void mapperTest(){
        userMapper.selectList(null).forEach(System.out::println);
    }


    @Test
    void mapperInsert(){
        User user = new User();
        user.setAge(20);
        user.setName("wdh");
        user.setEmail("123@qq.com");
        userMapper.insert(user);
        System.out.println(user.getId());
    }

    @Test
    void mapperDelete(){
//        int i = userMapper.deleteById(1518938509936988161L);
        // 根据map中设置的条件进行删除
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("name", "wdh");
//        map.put("age", "20");
//        userMapper.deleteByMap(map);

        // Preparing: DELETE FROM t_user WHERE id IN ( ? , ? , ? )
        userMapper.deleteBatchIds(Arrays.asList(new long[]{1L,2L,3L}));
    }

    @Test
    @Transient
    void mapperUpdate(){
        User user = new User();
        user.setId(4L);
        user.setName("wlh");
        user.setEmail("886@qq.com");
        // Preparing: UPDATE t_user SET name=?, email=? WHERE id=?
        userMapper.updateById(user);
    }

    @Test
    void mapperConfig(){
        Map<String, User> stringUserMap = userMapper.selectNameMap("4");
        System.out.println(stringUserMap.get("name"));
        Map<Integer, User> integerUserMap = userMapper.selectIdIntegerUserMap("4");
        System.out.println(integerUserMap);
    }

    @Test
    void serviceGet(){
        long count = userService.count();
        System.out.println("总条数:" + count);
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName(i+"用户");
            user.setAge(i+20);
            user.setEmail(i + "@qq.com");
            users.add(user);
        }
        userService.saveBatch(users);
    }

    @Test
    void userTest(){
        // 逻辑删除
        // 把对应的逻辑删除字段改变
        int i = userMapper.deleteById(1519135012160868372L);
    }
}
