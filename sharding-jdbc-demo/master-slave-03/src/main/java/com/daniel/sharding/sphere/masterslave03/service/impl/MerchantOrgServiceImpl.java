package com.daniel.sharding.sphere.masterslave03.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.daniel.sharding.sphere.masterslave03.entity.MerchantOrg;
import com.daniel.sharding.sphere.masterslave03.mapper.MerchantOrgMapper;
import com.daniel.sharding.sphere.masterslave03.service.IMerchantOrgService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 组织商户号关联表 服务实现类
 * </p>
 *
 * @author DaiZM
 * @since 2018-05-15
 */
@Service
public class MerchantOrgServiceImpl extends ServiceImpl<MerchantOrgMapper, MerchantOrg> implements IMerchantOrgService {


    @Override
    public List<MerchantOrg> findEnableOrgsOfMerchant(Long merchantId) {
        EntityWrapper<MerchantOrg> entityWrapper = new EntityWrapper<>();
        entityWrapper.where(" merchant_id={0} ",merchantId)
                .and(" enable={0} ",Boolean.TRUE);
        return selectList(entityWrapper);
    }
}
