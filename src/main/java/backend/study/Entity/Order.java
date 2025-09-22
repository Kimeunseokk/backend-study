package backend.study.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders") // order → orders로 변경
public class Order {

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

    // 주문번호
    private String usernum;

    // 주문상태 ex) 배달 완료 , 미완료
    private boolean status; 

    // 주문일자
    private LocalDate orderDate;

    @PrePersist // (DB에 처음 저장) 되기 전에 자동으로 실행되는 메서드
    public void onCreate() {
        this.orderDate = LocalDate.now(); // 저장 직전에 자동으로 오늘 날짜 설정
    }
}
