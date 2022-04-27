package com.wdh.mybatisplus;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.wdh.mybatisplus.enumAttribute.Sex;
import com.wdh.mybatisplus.mapper.UserMapper;
import com.wdh.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

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

    @Test
    void generate() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false"
                        , "root", "123456")
                .globalConfig(builder -> {
                    builder.author("wdh") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E://mybatisplus"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.wdh") // 设置父包名
                            .moduleName("mybatisplus") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E://mybatisplus")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
