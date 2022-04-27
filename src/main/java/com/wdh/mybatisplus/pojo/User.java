package com.wdh.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
// 设置实体类对应的表名
// @TableName("t_user")
public class User {

    // mybatisplus会默认把id当作主键
    // value 为设置数据库对应字段
    // type 为主键类型 AUTO为主键自增 NONE为雪花算法
    // 雪花算法是较好的分布式数据库主键策略 在每个表中主键都随着插入时间递增（不是AUTO的递增）
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    // 设置属性名对应的表的字段名
    @TableField("name")
    private String name;
    private Integer age;
    private String email;

    // 设置逻辑删除字段
    // 之后的所有增删改都会判断这个字段是否为0（未删除字段）
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;

}