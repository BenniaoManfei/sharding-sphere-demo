package com.daniel.sharding.sphere.shardingmasterslave03.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.io.Serializable;

/**
 * 接口类型
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
public enum ApiTypeEnum implements IEnum {

    /** 1001-交易信息推送接口(包括订单和退款) */
    TRADE(1001,"订单信息推送接口",false),

    /** 2001-补货计划/上架信息推送接口 */
    PLAN(2001,"补货计划/上架信息信息推送接口",false),

    ;
    private Integer value;
    private String name;
    /** 是否需要审核（不需要审核的将默认生成） */
    private Boolean needToPended;

    ApiTypeEnum(Integer value, String name,Boolean needToPended) {
        this.value = value;
        this.name = name;
        this.needToPended = needToPended;
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
    public static ApiTypeEnum getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (ApiTypeEnum statusEnum : ApiTypeEnum.values()) {
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
