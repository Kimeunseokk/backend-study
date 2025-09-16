package backend.study.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import backend.study.Entity.Menu;
import backend.study.Repository.MenuRepository;
import backend.study.Service.MenuService;


@Controller
@RequestMapping("/order")
public class OrderController {

    private final MenuService menuService;

    // 생성자 주입
    public OrderController(MenuService menuService) {
        this.menuService = menuService;
    }
    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id, Model model) {
        Menu menu = menuService.getMenu(id);
        model.addAttribute("menu", menu);
        return "Order";
    }
    
}
