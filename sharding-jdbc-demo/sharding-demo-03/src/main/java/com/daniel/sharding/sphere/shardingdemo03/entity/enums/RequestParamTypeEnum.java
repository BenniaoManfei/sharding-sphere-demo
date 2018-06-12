package com.daniel.sharding.sphere.shardingdemo03.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.io.Serializable;

/**
 * 请求参数类型枚举
 *
 * @author DaiZM
 * @date 2018/05/15
 * @since open-api-1.0
 *
 * @history:
 * <author>          <time>          <version>          <desc>
 * DaiZM             2018/05/15           open-api-1.0          created
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum RequestParamTypeEnum implements IEnum {

    /** 1-FORM */
    FORM(1,"FORM"),

    /** 2-JSON */
    JSON(2,"JSON"),

    ;
    private Integer value;
    private String name;

    RequestParamTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * 根据code值获取对应的枚举
     *
     * @param code
     * @return
     *
     * @author Daniel
     * @date 2017年9月12日
     *
     */
    @JsonCreator
    public static RequestParamTypeEnum getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (RequestParamTypeEnum statusEnum : RequestParamTypeEnum.values()) {
            if (statusEnum.value.intValue() == code.intValue()) {
                return statusEnum;
            }
        }
        return null;
    }

    @Override
    public Serializable getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
    
}
