package backend.study.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.study.Controller.MenuController;
import backend.study.Entity.Menu;
import backend.study.Repository.MenuRepository;
import jakarta.transaction.Transactional;

@Service
public class ManagerService {
    public final MenuRepository menuRepository;

    public ManagerService(MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }

    public List<Menu> getAllMenus(){ // 전체메뉴 검색
        return menuRepository.findAll();
    }

       // 메뉴 수정
    @Transactional
    public Menu updateMenu(Long id, Menu menuData) {
        Menu menu = menuRepository.findById(id).orElseThrow();
        menu.setName(menuData.getName());
        menu.setPrice(menuData.getPrice());
        menu.setSoldOut(menuData.isSoldOut());
        menu.setNum(menuData.getNum());
        return menu;
    }
    // 메뉴 삭제
    @Transactional
    public Menu deleteMenu(Long id){
        Menu menu = menuRepository.findById(id).orElseThrow();
        menuRepository.delete(menu);
        return menu;
    }
     @Transactional
    public Menu createMenu(Menu menu){
        return menuRepository.save(menu);
    }

}
