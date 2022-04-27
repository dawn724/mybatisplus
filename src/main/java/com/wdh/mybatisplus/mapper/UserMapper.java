package com.wdh.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdh.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User> {

    Map<String,User> selectNameMap(String id);

    Map<Integer,User> selectIdIntegerUserMap(String id);

    Page<User> selectPageVo(@Param("page") Page<User> page,@Param("age") Integer age);

}
