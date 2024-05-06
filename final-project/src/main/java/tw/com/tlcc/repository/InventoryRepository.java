package tw.com.tlcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.tlcc.domain.InventoryBean;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryBean, Integer>, InventoryRepositoryDAO {

}
