package backend.study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import backend.study.Entity.Menu;
import backend.study.Repository.MenuRepository;

@SpringBootTest
class StudyApplicationTests {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    void TestMenuSave() {
        // 메뉴 객체 생성
        Menu menu1 = new Menu();
        menu1.setName("아메리카노");
        menu1.setPrice(3500);
        menu1.setSoldOut(false);

        // DB에 저장 (H2 파일 DB 자동 생성)
        Menu saved = menuRepository.save(menu1);

        System.out.println("저장된 메뉴 ID: " + saved.getId());
    }
}
