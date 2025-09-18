package backend.study.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤 메뉴인지 참조
    @ManyToOne(fetch = FetchType.LAZY) // LAZY 로딩 권장
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    // 주문 수량
    @Column(nullable = false)
    private int quantity;

    // 요청사항 (ex: 덜 맵게, 많이 달게 등)
    @Column(length = 500)
    private String notes;
}
