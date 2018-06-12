package com.daniel.sharding.sphere.shardingdemo03.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.daniel.sharding.sphere.shardingdemo03.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统商户配 Mapper 接口
 * </p>
 *
 * @author Chris
 * @since 2018-05-07
 */
@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {

}
