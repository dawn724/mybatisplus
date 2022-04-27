package com.wdh.mybatisplus.pojo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdh.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
