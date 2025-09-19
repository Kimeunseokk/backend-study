package backend.study.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import backend.study.Entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByNum(int num);
    Page<Menu> findAll(Pageable pageable); // 반드시 Spring Data JPA Pageable 사용
}
