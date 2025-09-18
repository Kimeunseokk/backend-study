package backend.study.Service;

import backend.study.Repository.MenuDetailRepository;

public class MenuDetailService {
    private final MenuDetailRepository menuDetailRepository;

    public MenuDetailService(MenuDetailRepository menuDetailRepository){
        this.menuDetailRepository = menuDetailRepository;
    }

    
}
