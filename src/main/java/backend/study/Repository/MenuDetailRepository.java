package backend.study.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.study.Entity.OrderDetail;

public interface MenuDetailRepository extends JpaRepository<OrderDetail,Long> {

    
    
}
