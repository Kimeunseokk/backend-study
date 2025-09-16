package backend.study.Entity;

import java.lang.reflect.GenericArrayType;

import org.hibernate.annotations.DialectOverride.GeneratedColumn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    @Column(length = 100, nullable = false,columnDefinition = "TEXT")
    private String name; // 제품이름

    @Column(name = "PRICE")
    private int price; // 가격

    @Column(name = "SOLD_OUT")
    private boolean soldOut; // 품절여부

    @Column(name = "NUM")
    private int num; //수량 

    
}
