package tw.com.tlcc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.tlcc.domain.MemberBean;

@Repository
public interface MemberRepository extends 
JpaRepository<MemberBean, Integer>, MemberRepositoryDAO {

	Optional<MemberBean> findByMemberIdAndMemberPassword(String memberId, String memberPassword);
	
}


 
