package backend.study.Controller;

import java.lang.annotation.Repeatable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import backend.study.Entity.Menu;
import backend.study.Entity.Order;
import backend.study.Repository.MenuRepository;
import backend.study.Service.CartService;
import backend.study.Service.MenuService;
import backend.study.Service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/order")
public class OrderController {

    private final MenuService menuService;
    private final OrderService orderService;
    private final CartService cartService;

    // 생성자 주입
    public OrderController(MenuService menuService, OrderService orderService, CartService cartService) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.cartService = cartService;
    } 
    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id, Model model) {
        Menu menu = menuService.getMenu(id);
        model.addAttribute("menu", menu);
        return "Order";
    }

    @PostMapping("/add") // 기존 /order + /add → /order/add
    public String addOrder(@RequestParam Long menuId,
                       @RequestParam int quantity,
                       @RequestParam(required = false) String notes) {
    orderService.addOrder(menuId, quantity, notes); // 서비스에서 Order 객체 생성
    return "redirect:/menu/list";
}
    @PostMapping("/cartadd")
    public String addcart(@RequestParam Long menuId, @RequestParam Long cartId, @RequestParam int quantity,@RequestParam(required = false) String notes) {
        cartService.addToCart(cartId, menuId, quantity, notes);
        
        return "redirect:/menu/list" ;
    }

}
