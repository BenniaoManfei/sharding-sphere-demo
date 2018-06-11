package com.daniel.sharding.sphere.masterslave01.repository;

import com.daniel.sharding.sphere.masterslave01.MasterSlave01ApplicationTests;
import com.daniel.sharding.sphere.masterslave01.entity.Order;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;


@Slf4j
public class OrderRepositoryTest extends MasterSlave01ApplicationTests {

    @Autowired
    private OrderRepository orderRepository;


    @Test
    @Transactional
    public void insert() {
        Order order = new Order();
        Random random = new Random();
        order.setOrderId(1003L+random.nextLong());
        order.setUserId(2002);
        order.setStatus(RandomStringUtils.randomNumeric(4));
//        orderRepository.saveAndFlush(order);
        orderRepository.save(order);
        System.err.println(order);
    }

    @Test
    public void findAll() {

        List<Order> order = orderRepository.findAll();
        System.err.println(order);
    }

}