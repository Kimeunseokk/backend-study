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

    @Column(nullable = false, length = 100)
    private String name;


    @Column(name = "PRICE")
    private int price; // 가격

    @Column(name = "SOLD_OUT")
    private boolean soldOut; // 품절여부

    @Column(name = "NUM")
    private int num; //수량 

    private String imgpath;

    
}
