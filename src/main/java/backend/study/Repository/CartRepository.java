package backend.study.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.study.Entity.Cart;
import backend.study.Entity.Cartitem;
import backend.study.Entity.User;

public interface CartRepository extends JpaRepository<Cart, Long>{
    Optional<Cart> findByUserId(Long userId);
    List<Cart> findByuserId(Long userId);
}

