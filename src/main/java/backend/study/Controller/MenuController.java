package backend.study.Controller;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import backend.study.Service.MenuService;
import backend.study.Entity.Menu;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // @GetMapping  // GET /menu
    // public String getMenus(Model model) {
    //     List<Menu> menus = menuService.getAllMenus();
    //     model.addAttribute("menus", menus);
    //     return "Menu"; // templates/Menu.html
    // }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Menu> paging = this.menuService.getList(page,8);
        model.addAttribute("paging", paging);
        return "Menu_list";
    }

}
