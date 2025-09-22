package backend.study.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.study.Controller.MenuController;
import backend.study.Entity.Menu;
import backend.study.Entity.Order;
import backend.study.Repository.MenuRepository;
import backend.study.Repository.OrderRepository;
import jakarta.transaction.Transactional;

@Service
public class ManagerService {
    public final MenuRepository menuRepository;
    public final OrderRepository orderRepository;

    public ManagerService(MenuRepository menuRepository, OrderRepository orderRepository){
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
    }

    /////////// 메뉴관리 기능 /////////////////////
       
    public List<Menu> getAllMenus(){ // 전체메뉴 검색
        return menuRepository.findAll();
    }

    // 메뉴 수정
    @Transactional
    public Menu updateMenu(Long id, Menu menuData) {
        Menu menu = menuRepository.findById(id).orElseThrow();
        menu.setName(menuData.getName());
        menu.setPrice(menuData.getPrice());
        menu.setSoldOut(menuData.isSoldOut());
        menu.setNum(menuData.getNum());
        return menu;
    }
    // 메뉴 삭제
    @Transactional
    public Menu deleteMenu(Long id){
        Menu menu = menuRepository.findById(id).orElseThrow();
        menuRepository.delete(menu);
        return menu;
    }
    @Transactional
    public Menu createMenu(Menu menu){
        return menuRepository.save(menu);
    }

    @Transactional
    public Menu addMenu(Menu menu){
        return menuRepository.save(menu);
    }
    ///////////////////////////////////
    /// 주문 내역 관리 기능 ///////////////
    
    public List<Order> getAllOrders(){ // 전체 주문내역 출력
        return orderRepository.findAll();
    }

    // 주문내역 삭제
    @Transactional
    public Order deleteOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order);
        return order;
    }

    // 주문내역 수정
    @Transactional
    public Order updateOrder(Long id, Order orderdata){
        Order order = orderRepository.findById(id).orElseThrow();
        order.setQuantity(orderdata.getQuantity()); // 주문수량
        order.setNotes(orderdata.getNotes()); // 요청사항
        order.setStatus(orderdata.isStatus()); // 주문상태 
        order.setOrderDate(orderdata.getOrderDate()); // 주문일자
        return order;
    }

}
