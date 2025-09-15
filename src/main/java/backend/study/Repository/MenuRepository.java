package backend.study.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.study.Entity.Menu;

public interface MenuRepository extends JpaRepository<Menu,Long>{
    
}
