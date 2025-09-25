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

    }
}
