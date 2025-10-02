package backend.study.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.study.Entity.Cartitem;

public interface CartitemRepository extends JpaRepository<Cartitem, Long>{
    List<Cartitem> findByCart_UserIdAndOrderedFalse(Long cartrId);
}
