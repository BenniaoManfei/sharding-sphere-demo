package com.daniel.sharding.sphere.shardingdemo02.repository;

import com.daniel.sharding.sphere.shardingdemo02.ShardingDemo02ApplicationTests;
import com.daniel.sharding.sphere.shardingdemo02.entity.Order;
import com.daniel.sharding.sphere.shardingdemo02.entity.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class OrderRepositoryTest extends ShardingDemo02ApplicationTests {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderItemRepository orderItemRepository;

//    @Test
    @Transactional
    public void insert() {

        List<Long> orderIds = new ArrayList<>(10);
        System.out.println("1.Insert--------------");
        for (int i = 0; i < 1000; i++) {
            Order order = new Order();
            order.setUserId(51);
            order.setStatus("INSERT_TEST");
            orderRepository.insert(order);
            long orderId = order.getOrderId();
            orderIds.add(orderId);

            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setUserId(51);
            orderItemRepository.insert(item);
        }
        System.out.println(orderItemRepository.selectAll());
        System.out.println("2.Delete--------------");


    }
}