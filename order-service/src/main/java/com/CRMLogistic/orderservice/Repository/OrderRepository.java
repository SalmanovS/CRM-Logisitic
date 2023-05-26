package com.CRMLogistic.orderservice.Repository;

import com.CRMLogistic.orderservice.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select * from orders where departure_date = curdate() and order_status = 'Scheduled'",
            nativeQuery = true)
    List<Order> selectTodayOrders();

    @Query(value = "select * from orders where departure_date = curdate() +  1 ", nativeQuery = true)
    List<Order> selectTomorrowOrders();

    @Query(value = "select * from orders where departure_date > curdate() + 1", nativeQuery = true)
    List<Order> selectOtherDays();

    List<Order> findByStatus(String status);
}
