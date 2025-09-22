package backend.study.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import backend.study.Entity.Menu;
import backend.study.Entity.Order;
import backend.study.Service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    // ================== 메뉴 관리 기능 ========================
    @GetMapping
    public String managerPage(Model model) {
        //메뉴관리
        List<Menu> menus = managerService.getAllMenus();
        model.addAttribute("menus", menus);

        //주문내역관리
        List<Order> orders = managerService.getAllOrders();
        model.addAttribute("orders", orders);
        return "manager"; // templates/manager.html
    }

    // 메뉴 추가
    @PostMapping("/menu/add")
    public String addMenu(@ModelAttribute Menu menu) {
        managerService.addMenu(menu);
        return "redirect:/manager"; // 등록 후 새로고침
    }

    // 메뉴 수정
    @PostMapping("/menu/update/{id}")
    public String updateMenu(@PathVariable Long id, @ModelAttribute Menu menu) {
        managerService.updateMenu(id, menu);
        return "redirect:/manager"; // 수정 후 새로고침
    }

    // 메뉴 삭제
    @GetMapping("/menu/delete/{id}")
    public String deleteMenu(@PathVariable Long id) {
        managerService.deleteMenu(id);
        return "redirect:/manager"; // 삭제 후 새로고침
    }

    // ================== 주문 내역 관리 기능 ========================
    // 주문 상태 변경
    @PostMapping("/order/update/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Order order) {
        managerService.updateOrder(id, order);
        return "redirect:/manager";
    }

    // 주문 삭제
    @GetMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        managerService.deleteOrder(id);
        return "redirect:/manager";
    }

}
