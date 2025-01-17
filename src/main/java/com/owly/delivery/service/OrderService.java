package com.owly.delivery.service;

import com.owly.delivery.dao.OrderDao;
import com.owly.delivery.entity.Orders;
import com.owly.delivery.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class OrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDao orderDao;

    //save Order service, when confirm the order, send the order info to Orders table
    public void saveOrder(Orders order) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = userService.getUser(username);

        orderDao.save(order);

    }

    public int getOrderIdByCreateTime(Timestamp createTime) {

        Orders order = orderDao.getOrderByCreateTime(createTime);
        return order.getOrderId();
    }

}