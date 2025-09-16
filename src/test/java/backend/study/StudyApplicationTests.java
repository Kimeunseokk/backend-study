package backend.study;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import backend.study.Entity.Menu;
import backend.study.Repository.MenuRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
class StudyApplicationTests {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    @Transactional
    @Rollback(false)
    void TestMenuSave() {
        // 메뉴 객체 생성
        Menu menu1 = new Menu();
        menu1.setName("아메리카노");
        menu1.setPrice(3500);
        menu1.setSoldOut(false);
        menu1.setNum(10);

        Menu menu2 = new Menu();
        menu2.setName("아이스바닐라라떼");
        menu2.setPrice(4500);
        menu2.setSoldOut(true);
        menu2.setNum(0);

        Optional<Menu> m = menuRepository.findByNum(10);

        

        // DB에 저장 (H2 파일 DB 자동 생성)
        menuRepository.save(menu1);
        menuRepository.save(menu2);
        menuRepository.flush(); // 강제 반영
    }
}
