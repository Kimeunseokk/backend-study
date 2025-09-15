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

    @Transactional
    public Menu createMenu(Menu menu){
        return menuRepository.save(menu);
    }

    public Menu getMenu(Long id){
        return menuRepository.findById(id).orElseThrow();
    }

     // 메뉴 수정
    @Transactional
    public Menu updateMenu(Long id, Menu menuData) {
        Menu menu = menuRepository.findById(id).orElseThrow();
        menu.setName(menuData.getName());
        menu.setPrice(menuData.getPrice());
        menu.setSoldOut(menuData.isSoldOut());
        return menu;
    }
    // 메뉴 삭제
    @Transactional
    public Menu deleteMenu(Long id){
        Menu menu = menuRepository.findById(id).orElseThrow();
        menuRepository.delete(menu);
        return menu;
    }

}
