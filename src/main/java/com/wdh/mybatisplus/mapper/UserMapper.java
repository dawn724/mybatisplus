package com.wdh.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdh.mybatisplus.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User> {

    Map<String,User> selectNameMap(String id);

    Map<Integer,User> selectIdIntegerUserMap(String id);

}
