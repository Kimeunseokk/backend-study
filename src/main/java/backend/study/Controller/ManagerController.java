package backend.study.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import backend.study.Entity.Menu;
import backend.study.Service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    // 관리자 페이지 화면 - 메뉴 목록 표시
    @GetMapping
    public String managerPage(Model model) {
        List<Menu> menus = managerService.getAllMenus();
        model.addAttribute("menus", menus);
        return "manager"; // templates/manager.html
    }

    // 메뉴 추가
    @PostMapping("/add")
    public String addMenu(@ModelAttribute Menu menu) {
        managerService.createMenu(menu);
        return "redirect:/manager"; // 등록 후 새로고침
    }

    // 메뉴 수정
    @PostMapping("/update/{id}")
    public String updateMenu(@PathVariable Long id, @ModelAttribute Menu menu) {
        managerService.updateMenu(id, menu);
        return "redirect:/manager"; // 수정 후 새로고침
    }

    // 메뉴 삭제
    @GetMapping("/delete/{id}")
    public String deleteMenu(@PathVariable Long id) {
        managerService.deleteMenu(id);
        return "redirect:/manager"; // 삭제 후 새로고침
    }
}
