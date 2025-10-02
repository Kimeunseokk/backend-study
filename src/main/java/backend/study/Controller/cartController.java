package backend.study.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import backend.study.Entity.Cartitem;
import backend.study.Entity.Menu;
import backend.study.Entity.User;
import backend.study.Service.CartService;
import backend.study.Service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;


    @GetMapping("/list")
    public String getCartList(Model model, HttpSession session) {
    Long userId = ((User) session.getAttribute("LoginUser")).getId(); // 세션에서 유저 id 가져오기
    List<Cartitem> items = cartService.getCartMenu(userId);
    model.addAttribute("cartitems", items);

    return "Cart_list";
    }

    @PostMapping("/order")
    public String addCartOrder(HttpSession session) {
        // 세션에서 로그인한 유저 가져오기
        User loginUser = (User) session.getAttribute("LoginUser");
        if (loginUser == null) {
            // 로그인 안 된 경우 처리 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/login/page";
        }

        Long userId = loginUser.getId();
        cartService.addCartOrder(userId);

        return "redirect:/menu/list";
}
    

    
}
