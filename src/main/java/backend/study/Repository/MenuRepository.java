package backend.study.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.study.Entity.Menu;
import java.util.List;


public interface MenuRepository extends JpaRepository<Menu,Long>{
    Optional <Menu> findByNum(int num);
}
