package backend.study.Repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.study.Entity.Order;


public interface OrderRepository extends JpaRepository<Order,Long> {
    long countByOrderDate(LocalDate orderDate);
}
