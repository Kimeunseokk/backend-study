package backend.study.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.study.Entity.Cart;
import backend.study.Entity.Cartitem;
import backend.study.Entity.Menu;
import backend.study.Entity.Order;
import backend.study.Repository.CartRepository;
import backend.study.Repository.CartitemRepository;
import backend.study.Repository.MenuRepository;
import backend.study.Repository.OrderRepository;
import backend.study.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final MenuRepository menuRepository;
    private final CartRepository cartRepository;
    private final CartitemRepository cartitemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    // 장바구니에 메뉴 추가
    public Cartitem addToCart(Long userId, Long menuId, int quantity, String note) {
            Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(userRepository.findById(userId)
                                    .orElseThrow(() -> new RuntimeException("User not found")));
                    return cartRepository.save(newCart);
                });

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

            Cartitem cartitem = new Cartitem();
            cartitem.setMenu(menu);
            cartitem.setQuantity(quantity);
            cartitem.setNotes(note);
            cartitem.setOrdered(false);

            // Cart에 CartItem 추가
            cart.addItem(cartitem);

            // CartItem 저장 (Cascade 때문에 cartRepository.save(cart)해도 됨)
            return cartitemRepository.save(cartitem);
    }

    // 아직 주문되지 않은 유저 장바구니 조회
    public List<Cartitem> getCartMenu(Long userId) {
        return cartitemRepository.findByCart_UserIdAndOrderedFalse(userId);
    }
    @Transactional
    public void addCartOrder(Long userId) {
        List<Cartitem> items = cartitemRepository.findByCart_UserIdAndOrderedFalse(userId);
        for(Cartitem item : items){
            Order order = new Order();
            order.setMenu(item.getMenu());
            order.setNotes(item.getNotes());
            order.setQuantity(item.getQuantity());
            order.setStatus(false);
            item.setOrdered(true); 
            orderRepository.save(order);
        }
    }
}