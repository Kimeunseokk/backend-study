package backend.study.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import backend.study.Entity.Menu;
import backend.study.Entity.Order;
import backend.study.Repository.OrderRepository;
import jakarta.transaction.Transactional;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    
    public String generateOrderNum() {
    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    long seq = orderRepository.countByOrderDate(LocalDate.now()) + 1; 
    return today + "-" + String.format("%04d", seq); // 예: 20250922-0001
    }

    public List<Order> getAllMenus(){ // 전체 주문내역 출력
        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    @Transactional
    public Order addOrder(Order order){
        return orderRepository.save(order);
    }

    // 주문내역 삭제
    @Transactional
    public Order deleteOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order);
        return order;
    }

    public Order updateOrder(Long id, Order orderdata){
        Order order = orderRepository.findById(id).orElseThrow();
        order.setQuantity(orderdata.getQuantity()); // 주문수량
        order.setNotes(orderdata.getNotes()); // 요청사항
        order.setStatus(orderdata.isStatus()); // 주문상태 
        order.setOrderDate(orderdata.getOrderDate()); // 주문일자
        return order;
    }
     

    
}
