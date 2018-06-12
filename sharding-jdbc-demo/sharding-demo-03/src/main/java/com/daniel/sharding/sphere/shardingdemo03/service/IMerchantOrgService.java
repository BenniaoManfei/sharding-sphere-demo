package com.daniel.sharding.sphere.shardingdemo03.service;


import com.baomidou.mybatisplus.service.IService;
import com.daniel.sharding.sphere.shardingdemo03.entity.MerchantOrg;

import java.util.List;

/**
 * <p>
 * 组织商户号关联表 服务类
 * </p>
 *
 * @author DaiZM
 * @since 2018-05-15
 */
public interface IMerchantOrgService extends IService<MerchantOrg> {

    /**
     * 查询某个商户号适用的范围
     *
     * @param merchantId
     * @return java.util.List<com.bangmart.openapi.platform.merchant.entity.MerchantOrg>
     *
     * @since open-api-1.0
     * @author DaiZM
     * @date 2018/05/15
     */
    List<MerchantOrg> findEnableOrgsOfMerchant(Long merchantId) ;

}
