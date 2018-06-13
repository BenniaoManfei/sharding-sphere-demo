package com.daniel.sharding.sphere.shardingmasterslave03.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;


import com.daniel.sharding.sphere.shardingmasterslave03.entity.enums.MerchantDataExchangeTypeEnum;
import com.daniel.sharding.sphere.shardingmasterslave03.entity.enums.MerchantStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统商户配
 * </p>
 *
 * @author Chris
 * @since 2018-05-07
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_merchant")
public class Merchant extends Model<Merchant> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 创建商户号的组织id */
    private Long orgId;

    /**
     * 商户号
     */
    private String mid;
    /**
     * 密钥
     */
    private String keyt;
    /**
     * 是否限制ip
     */
    private Boolean ipRestricted;
    /**
     * ip白名单
     */
    private String[] whitelist;
    /**
     * 状态
     */
    private MerchantStatusEnum status;

    /** 系统地址 */
    private String baseUrl;

    /** 数据交换类型 */
    private MerchantDataExchangeTypeEnum dataExchangeType = MerchantDataExchangeTypeEnum.STANDARD;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }



}
