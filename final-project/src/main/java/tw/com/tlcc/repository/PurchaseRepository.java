package tw.com.tlcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import tw.com.tlcc.domain.PurchaseBean;

public interface PurchaseRepository extends JpaRepository<PurchaseBean, Integer>, PurchaseRepositoryDAO {

}