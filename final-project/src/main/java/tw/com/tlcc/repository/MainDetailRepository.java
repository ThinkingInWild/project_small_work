package tw.com.tlcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.tlcc.domain.MainDetailBean;

public interface MainDetailRepository extends JpaRepository<MainDetailBean,String>, MainDetailRepositoryDAO{

}
