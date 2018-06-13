package com.daniel.sharding.sphere.shardingmasterslave02.repository;

import com.daniel.sharding.sphere.shardingmasterslave02.ShardingMasterslave02ApplicationTests;
import com.daniel.sharding.sphere.shardingmasterslave02.entity.Order;
import com.daniel.sharding.sphere.shardingmasterslave02.entity.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Slf4j
public class OrderRepositoryTest extends ShardingMasterslave02ApplicationTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    public void insert() {
        System.out.println("1.Insert--------------");
        Random random = new Random();
        for (long i = 0; i < 1000; i++) {
            int userId = random.nextInt(100);
            Order order = new Order();
            order.setUserId(userId);
            order.setStatus("INSERT_TEST");
            orderRepository.insert(order);

            System.err.println(Long.toBinaryString(order.getOrderId()));
            OrderItem item = new OrderItem();
            item.setOrderId(order.getOrderId());
            item.setUserId(userId);

            orderItemRepository.insert(item);
        }
    }
}