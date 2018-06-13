package com.daniel.sharding.sphere.shardingmasterslave01.repository;

import com.daniel.sharding.sphere.shardingmasterslave01.ShardingMasterslave01ApplicationTests;
import com.daniel.sharding.sphere.shardingmasterslave01.entity.Order;
import com.daniel.sharding.sphere.shardingmasterslave01.entity.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;


@Slf4j
public class OrderRepositoryTest extends ShardingMasterslave01ApplicationTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    public void insert() {
        Random random = new Random();
        for (long i = 0; i < 1000; i++) {
            int userId = random.nextInt(100);
            Order order = new Order();
            order.setUserId(userId);
            order.setStatus("INSERT_TEST");
            long orderId = orderRepository.save(order).getOrderId();

            System.err.println(Long.toBinaryString(orderId));
            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setUserId(userId);

            orderItemRepository.save(item);
        }
    }

    @Test
    public void findAll() {
//        List<Order> orders = orderRepository.findAll();
        Order orders = orderRepository.findOne(213624111115537414L);
        log.error("findAll(logged by DaiZM):<><>【{}】",orders);
    }

}