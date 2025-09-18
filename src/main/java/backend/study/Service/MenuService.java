package backend.study.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.study.Entity.Menu;
import backend.study.Repository.MenuRepository;
import jakarta.transaction.Transactional;


@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository){ // 생성사 주입
        this.menuRepository = menuRepository;
    }

    public List<Menu> getAllMenus(){ // 전체메뉴 검색
        return menuRepository.findAll();
    }


    public Menu getMenu(Long id){
        return menuRepository.findById(id).orElseThrow();
    }

   

}
