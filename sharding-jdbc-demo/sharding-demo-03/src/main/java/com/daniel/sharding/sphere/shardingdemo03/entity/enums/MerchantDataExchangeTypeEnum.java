package com.daniel.sharding.sphere.shardingdemo03.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.io.Serializable;

/**
 * 数据交换类型
 *
 * @author DaiZM
 * @date 2018/05/14
 * @since open-api-1.0
 *
 * @history:
 * <author>          <time>          <version>          <desc>
 * DaiZM             2018/05/14           open-api-1.0           created
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum MerchantDataExchangeTypeEnum implements IEnum {

    /** 1-标准输出 */
    STANDARD(1,"标准输出"),

    /** 2-家家悦 */
    JJY(2,"家家悦"),

    /** 3-中百 */
    ZHONGBAI(3,"中百"),
    ;
    private Integer value;
    private String name;

    MerchantDataExchangeTypeEnum(Integer value, String name) {
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
    public static MerchantDataExchangeTypeEnum getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (MerchantDataExchangeTypeEnum statusEnum : MerchantDataExchangeTypeEnum.values()) {
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