package com.daniel.sharding.sphere.masterslave03.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.io.Serializable;

/**
 * 商户接口状态枚举
 *
 * @author DaiZM
 * @date 2018/05/15
 * @since open-api-1.0
 * @version open-api-1.0 created
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum MerchantUriStatusEnum implements IEnum {

    /** 1-待审核 */
    PENDING(1,"待审核"),

    /** 2-已生效 */
    NORMAL(2,"已生效"),

    /** 3-接口异常 */
    ABNORMAL(3,"接口异常"),

    /** 4-已失效 */
    EXPIRED(4,"已失效"),

    ;
    private Integer value;
    private String name;

    MerchantUriStatusEnum(Integer value, String name) {
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
    public static MerchantUriStatusEnum getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (MerchantUriStatusEnum statusEnum : MerchantUriStatusEnum.values()) {
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