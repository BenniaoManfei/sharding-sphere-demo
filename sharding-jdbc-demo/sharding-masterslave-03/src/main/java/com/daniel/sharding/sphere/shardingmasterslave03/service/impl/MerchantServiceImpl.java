package com.daniel.sharding.sphere.shardingmasterslave03.service.impl;



import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.daniel.sharding.sphere.shardingmasterslave03.entity.Merchant;
import com.daniel.sharding.sphere.shardingmasterslave03.mapper.MerchantMapper;
import com.daniel.sharding.sphere.shardingmasterslave03.service.IMerchantService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 组织商户号关联表 服务实现类
 * </p>
 *
 * @author DaiZM
 * @since 2018-05-15
 */
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements IMerchantService {

}
