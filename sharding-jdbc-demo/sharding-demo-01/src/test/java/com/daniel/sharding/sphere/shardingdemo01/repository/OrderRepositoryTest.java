package com.daniel.sharding.sphere.shardingdemo01.repository;

import com.daniel.sharding.sphere.shardingdemo01.ShardingDemo01ApplicationTests;
import com.daniel.sharding.sphere.shardingdemo01.entity.Order;
import com.daniel.sharding.sphere.shardingdemo01.entity.OrderItem;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
public class OrderRepositoryTest extends ShardingDemo01ApplicationTests {

    @Autowired
    private OrderRepository orderRepository;

    @Resource
    private OrderItemRepository orderItemRepository;

    @Test
//    @Transactional
    public void insert() {
        DefaultKeyGenerator generator = new DefaultKeyGenerator();
        for (long i = 0; i < 1000; i++) {
            Order order = new Order();
            order.setUserId(51);
            order.setStatus("INSERT_TEST");
            long orderId = orderRepository.save(order).getOrderId();

            System.err.println(Long.toBinaryString(orderId));
            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setUserId(51);


            orderItemRepository.save(item);
        }
    }

    @Test
    public void findAll() {
        List<Order> orders = orderRepository.findAll();

        log.error("findAll(logged by DaiZM):<><>【{}】",orders);
    }

}