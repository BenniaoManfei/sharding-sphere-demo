package com.daniel.sharding.sphere.shardingdemo03.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.io.Serializable;

/**
 * 请求方法类型枚举
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
public enum RequestMethodTypeEnum implements IEnum {

    /** 1-POST */
    POST(1,"POST"),

    /** 2-GET */
    GET(2,"GET"),

    /** 3-PUT */
    PUT(3,"PUT"),

    /** 4-DELETE */
    DELETE(4,"DELETE"),
    ;
    private Integer value;
    private String name;

    RequestMethodTypeEnum(Integer value, String name) {
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
    public static RequestMethodTypeEnum getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (RequestMethodTypeEnum statusEnum : RequestMethodTypeEnum.values()) {
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
