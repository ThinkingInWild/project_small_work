package tw.com.tlcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.tlcc.domain.DetailBean;

public interface DetailRepository extends JpaRepository<DetailBean, Integer>, DetailRepositoryDAO{
	
//	Optional<DetailBean> findById(@Nullable Integer id);
//	
//	Boolean existsById(@Nullable Integer id);
//	
//	Integer deleteById(Integer id);

}
