package com.daniel.sharding.sphere.masterslave03.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.daniel.sharding.sphere.masterslave03.MasterSlave03ApplicationTests;
import com.daniel.sharding.sphere.masterslave03.entity.Merchant;
import com.daniel.sharding.sphere.masterslave03.entity.enums.MerchantStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Slf4j
public class IMerchantServiceTest extends MasterSlave03ApplicationTests {

    @Autowired
    private IMerchantService merchantService;

    @Test
    public void findAll() {
        EntityWrapper<Merchant> ew = new EntityWrapper<>();
        ew.where(" status={0} ", MerchantStatusEnum.NORMAL);
        List<Merchant> merchants = merchantService.selectList(ew);

        log.error("findAll(logged by DaiZM):<><>【{}】",merchants);
    }

}