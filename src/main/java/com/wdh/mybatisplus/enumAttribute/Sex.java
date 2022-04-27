package com.wdh.mybatisplus.enumAttribute;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum Sex {
    MALE(1,"男"), FEMALE(2,"女");


    // 加到枚举类的字段上
    @EnumValue
    private final Integer sex;
    private final String sexName;

    Sex(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
