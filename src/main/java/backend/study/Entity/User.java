package backend.study.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤 메뉴인지 참조
    @ManyToOne(fetch = FetchType.LAZY) // LAZY 로딩 권장
    @JoinColumn(name = "Order_id", nullable = false)
    private Order order;

    private Long kakaoId;          // ✅ 카멜케이스
    private String email;
    private String nickname;
    private String profileImageUrl; // ✅ 카멜케이스
}
