package com.daniel.sharding.sphere.shardingmasterslave03.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;


import com.daniel.sharding.sphere.shardingmasterslave03.entity.enums.ApiTypeEnum;
import com.daniel.sharding.sphere.shardingmasterslave03.entity.enums.MerchantDataExchangeTypeEnum;
import com.daniel.sharding.sphere.shardingmasterslave03.entity.enums.RequestMethodTypeEnum;
import com.daniel.sharding.sphere.shardingmasterslave03.entity.enums.RequestParamTypeEnum;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 发送数据到第三方记录
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
@TableName("t_remote_request_record")
public class RemoteRequestRecord extends Model<RemoteRequestRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商户id
     */
    private Long merchantId;
    /**
     * 商户号
     */
    private String mid;
    /**
     * 接口类型
     */
    private ApiTypeEnum apiType;

    /** 数据交换类型 */
    private MerchantDataExchangeTypeEnum dataExchangeType = MerchantDataExchangeTypeEnum.STANDARD;


    /**
     * 发送时间
     */
    private Date postTime;
    /**
     * 地址
     */
    private String url;
    /**
     * 参数
     */
    private String param;

    /**
     * 数据类型:1-form,2-json
     */
    private RequestParamTypeEnum paramType;

    /**
     * 请求参数：1-post;2-get;3-put;4-delete
     */
    private RequestMethodTypeEnum method;

    /**
     * 响应时间
     */
    private Date responseTime;
    /**
     * 正确响应参数(json)
     */
    private String responseBody;
    /**
     * 异常堆栈信息
     */
    private String exceptionStack;
    /**
     * 创建时间
     */
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
