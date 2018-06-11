package com.daniel.sharding.sphere.masterslave02.repository;

import com.daniel.sharding.sphere.masterslave02.MasterSlave02ApplicationTests;
import com.daniel.sharding.sphere.masterslave02.entity.OrderItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class OrderItemRepositoryTest extends MasterSlave02ApplicationTests {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    public void selectAll() {
        List<OrderItem> items = orderItemRepository.selectAll();
        System.err.println(items);
    }
}