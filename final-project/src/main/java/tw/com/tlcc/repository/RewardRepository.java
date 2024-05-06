package tw.com.tlcc.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import tw.com.tlcc.domain.RewardBean;

public interface RewardRepository extends JpaRepository<RewardBean, Integer>,RewardRepositoryDAO{



}

