package backend.study.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 장바구니 주인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 장바구니에 담긴 아이템들
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cartitem> items = new ArrayList<>();

    // CartItem 추가 시 편리하게 쓰는 헬퍼 메서드
    public void addItem(Cartitem item) {
        items.add(item);
        item.setCart(this);
    }

    // CartItem 제거 시
    public void removeItem(Cartitem item) {
        items.remove(item);
        item.setCart(null);
    }
}
