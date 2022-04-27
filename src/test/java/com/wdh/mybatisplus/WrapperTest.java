package com.wdh.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wdh.mybatisplus.mapper.UserMapper;
import com.wdh.mybatisplus.pojo.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

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

    @Test
    void update(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        // 修改年龄(大于20岁，姓名中以2开头)，或者email为null的用户
        // UPDATE t_user SET name=?, age=?
        // WHERE is_deleted=0
        // AND (age > ? AND name LIKE ? OR email IS NULL)
        userQueryWrapper.gt("age","20")
                        .likeRight("name","2")
                        .or()
                        .isNull("email");
        User user = new User();
        user.setName("wdh");
        user.setAge(21);
        userMapper.update(user,userQueryWrapper);
    }

    @Test
    void updateCondition(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        // 修改年龄大于20岁，(姓名中以2开头，或者email)为null的用户
        // UPDATE t_user SET name=?, age=? WHERE is_deleted=0 AND (age > ? AND (name LIKE ? OR email IS NULL))
        userQueryWrapper.gt("age","20")
                .and(p->p.likeRight("name","3").or().isNull("email"));
        User user = new User();
        user.setName("nihao");
        user.setAge(25);
        userMapper.update(user,userQueryWrapper);
    }

    @Test
    void selectAssemble(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("name", "age");
        List<Map<String, Object>> maps = userMapper.selectMaps(userQueryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void selectzi(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.inSql("id", "select id from t_user where id < 10");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void updateWrapper(){
        // 修改年龄大于20岁，(姓名中以2开头，或者email为null)的用户
        // UPDATE t_user SET name=?,age=?
        // WHERE is_deleted=0 AND (age > ? AND (name LIKE ? OR email IS NULL))
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.gt("age","20")
                .and(p->p.likeRight("name","3").or().isNull("email"));
        userUpdateWrapper.set("name","汪德厚").set("age","22");
        userMapper.update(null, userUpdateWrapper);
    }

    @Test
    void selectCondition(){
        // 查询年龄大于20岁，(姓名中以2开头，或者email为null)的用户
        // SELECT id,name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age > ?)
        String age = "20";
        String name = "5";
        String email = null;
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.gt(StringUtils.isNotBlank(age), "age", "20")
                .likeRight(StringUtils.isNotBlank(name), "name", name)
                .eq(email != null,"email",email);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void selectLambda() {
        // 修改年龄大于20岁，(姓名中以2开头，或者email为null)的用户
        String age = "20";
        String name = "5";
        String email = null;
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.gt(StringUtils.isNotBlank(age), User::getAge, age)
                .likeRight(StringUtils.isNotBlank(name), User::getName, name)
                .eq(email != null,User::getEmail,email);
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void updateLambda(){
        String age = "20";
        String name = "5";
        String email = null;
        LambdaUpdateWrapper<User> userLambdaQueryWrapper = new LambdaUpdateWrapper<>();
        userLambdaQueryWrapper.gt(StringUtils.isNotBlank(age), User::getAge, age)
                .likeRight(StringUtils.isNotBlank(name), User::getName, name)
                .eq(email != null,User::getEmail,email);
        User user = new User();
        user.setName("wdh");
        user.setAge(21);
        userMapper.update(user,userLambdaQueryWrapper);
    }
}
