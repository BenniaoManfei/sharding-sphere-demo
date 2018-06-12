package com.daniel.sharding.sphere.shardingdemo03.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 组织商户号关联表
 * </p>
 *
 * @author DaiZM
 * @since 2018-05-15
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_merchant_org")
public class MerchantOrg extends Model<MerchantOrg> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 组织id
     */
    private Long orgId;
    /**
     * 父级组织id
     */
    private Long parentId;
    /**
     * 该条线的顶级组织id
     */
    private Long topOrgId;
    /**
     * 商户号id
     */
    private Long merchantId;

    /**
     * 是否启用
     */
    private Boolean enable;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
