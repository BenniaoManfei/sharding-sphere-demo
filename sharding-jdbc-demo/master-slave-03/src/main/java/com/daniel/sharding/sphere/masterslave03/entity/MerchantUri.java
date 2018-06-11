package com.daniel.sharding.sphere.masterslave03.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.daniel.sharding.sphere.masterslave03.entity.enums.ApiTypeEnum;
import com.daniel.sharding.sphere.masterslave03.entity.enums.MerchantUriStatusEnum;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 商户接口映射表
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
@TableName("sys_merchant_uri")
public class MerchantUri extends Model<MerchantUri> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商户号id
     */
    private Long merchantId;
    /**
     * 接口类型
     */
    private ApiTypeEnum apiType;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 接口状态
     */
    private MerchantUriStatusEnum status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
