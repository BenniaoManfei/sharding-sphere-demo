package com.daniel.sharding.sphere.masterslave03.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.daniel.sharding.sphere.masterslave03.entity.enums.ApiTypeEnum;
import com.daniel.sharding.sphere.masterslave03.entity.enums.MerchantDataExchangeTypeEnum;
import com.daniel.sharding.sphere.masterslave03.entity.enums.MerchantStatusEnum;
import com.daniel.sharding.sphere.masterslave03.entity.enums.MerchantUriStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

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

    @TableField(exist=false)
    private Map<ApiTypeEnum, MerchantUri> merchantUris;

    /** 是否含有某个接口的生效权限 */
    public boolean hasApi(ApiTypeEnum api) {
        if (merchantUris == null) {
            return false;
        }
        MerchantUri uri = merchantUris.get(api);
        return MerchantUriStatusEnum.NORMAL.equals(uri.getStatus());
    }

    /** 返回某个接口的路径 */
    public String getUriOfApi(ApiTypeEnum api) {
        if (merchantUris == null) {
            return null;
        }
        MerchantUri uri = merchantUris.get(api);

        return uri.getRequestUri();
    }


}
