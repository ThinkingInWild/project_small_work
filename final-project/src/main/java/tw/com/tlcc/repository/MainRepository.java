package tw.com.tlcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.tlcc.domain.MainBean;

public interface MainRepository extends JpaRepository<MainBean, String>, MainRepositoryDAO{

}
