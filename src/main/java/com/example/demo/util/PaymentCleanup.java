package com.example.demo.util;

import com.example.demo.Entity.Order;
import com.example.demo.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PaymentCleanup {

    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(fixedRate = 600000) // 10 phút
    public void expirePendingPayments(){

        LocalDateTime tenMinutesAgo =
                LocalDateTime.now().minusMinutes(10);

        List<Order> expired =
                orderRepository.findExpiredOrders(tenMinutesAgo);

        for(Order p : expired){
            p.setStatus("EXPIRED");
        }

        orderRepository.saveAll(expired);

        System.out.println("Expired payments: " + expired.size());
    }
}