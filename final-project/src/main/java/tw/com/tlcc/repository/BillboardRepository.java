package tw.com.tlcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.tlcc.domain.BillboardBean;

@Repository
public interface BillboardRepository extends JpaRepository<BillboardBean, Integer>, BillboardRepositoryDAO{

}
