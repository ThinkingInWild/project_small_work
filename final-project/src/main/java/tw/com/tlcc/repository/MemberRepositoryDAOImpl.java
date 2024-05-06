package tw.com.tlcc.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.com.tlcc.domain.MemberBean;
 
@Repository
public class MemberRepositoryDAOImpl implements MemberRepositoryDAO {
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override 
	public List<MemberBean> find(JSONObject obj) {
		//把JSONObject的參數值倒入變數
		Integer	id = obj.isNull("id")||obj.getInt("id")==0 ? null : obj.getInt("id");
		String memberId = obj.isNull("memberId")||obj.getString("memberId").isEmpty() ? null : obj.getString("memberId");
		String memberPassword = obj.isNull("memberPassword") ? null : obj.getString("memberPassword");
		String memberName = obj.isNull("memberName")||obj.getString("memberName").isEmpty() ? null : obj.getString("memberName");
		String email = obj.isNull("email")||obj.getString("email").isEmpty() ? null : obj.getString("email") ;
		Integer gender = obj.isNull("gender") ? null : obj.getInt("gender");
		String memberAddress  = obj.isNull("memberAddress")||obj.getString("memberAddress").isEmpty() ? null : obj.getString("memberAddress") ;
		Integer discountPointsLeft = obj.isNull("discountPointsLeft") ? null : obj.getInt("discountPointsLeft") ;
		String signUpDate = obj.isNull("signUpDate")||obj.getString("signUpDate").isEmpty() ? null : obj.getString("signUpDate") ;
		String finalLogInDate = obj.isNull("finalLogInDate")||obj.getString("finalLogInDate").isEmpty()  ? null : obj.getString("finalLogInDate");
		String accessLevel = obj.isNull("accessLevel")||obj.getString("accessLevel").isEmpty() ? null : obj.getString("accessLevel");
		Integer couponQuantity = obj.isNull("couponQuantity") ? null : obj.getInt("couponQuantity");
		
		String sort = obj.isNull("sort") ? "id" : obj.getString("sort");
		String order = obj.isNull("order") ? "asc" : obj.getString("order");
		Integer start = obj.isNull("start") ? 0 : obj.getInt("start");
		Integer rows = obj.isNull("rows") ? null : obj.getInt("rows");
		
		//建query
		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<MemberBean> criteria = builder.createQuery(MemberBean.class);
		
		//From member
		Root<MemberBean> root = criteria.from(MemberBean.class);
		
		//Predicate(查詢條件) = 添加where後面的表達式。
		List<Predicate> predicates = new ArrayList<>();
		//把變數的值倒入predicates
		if(id!=null) {	predicates.add(builder.equal(root.get("id"), id));	}
		if(memberId!=null) {	predicates.add(builder.like(root.get("memberId"), "%"+memberId+"%"));	}		
		if(memberPassword!=null) {	predicates.add(builder.equal(root.get("memberPassword"), memberPassword));	}
		if(memberName!=null) {	predicates.add(builder.like(root.get("memberName"), "%"+memberName+"%"));	}
		if(email!=null) {	predicates.add(builder.like(root.get("email"), "%"+email+"%"));	}
		if(gender!=null) {	predicates.add(builder.equal(root.get("gender"), gender));	}
		if(memberAddress!=null) {	predicates.add(builder.like(root.get("memberAddress"), "%"+memberAddress+"%"));	}
		if(discountPointsLeft!=null) {	predicates.add(builder.equal(root.get("discountPointsLeft"), discountPointsLeft));	}
		if(signUpDate!=null) {	predicates.add(builder.equal(root.get("signUpDate"), signUpDate));	}
		if(finalLogInDate!=null) {	predicates.add(builder.equal(root.get("finalLogInDate"), finalLogInDate));	}
		if(accessLevel!=null) {	predicates.add(builder.like(root.get("accessLevel"), "%"+accessLevel+"%"));	}
		if(couponQuantity!=null) {	predicates.add(builder.equal(root.get("couponQuantity"), couponQuantity));	}
		
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[0]);
			criteria = criteria.where(array);
		}
		
		if("desc".equals(order)) {
			criteria = criteria.orderBy(builder.desc(root.get(sort)));
		} else {
			criteria = criteria.orderBy(builder.asc(root.get(sort)));
		}
		
		TypedQuery<MemberBean> typedQuery = this.getSession().createQuery(criteria);
		if(rows!=null) {
			typedQuery = typedQuery.setFirstResult(start)
					.setMaxResults(rows);
		}
		
		List<MemberBean> result = typedQuery.getResultList();
		if(result!=null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
		
	}
	
	@Override
	public Long count(JSONObject obj) {
		Integer	id = obj.isNull("id")||obj.getInt("id")==0  ? null : obj.getInt("id");
		String	memberId = obj.isNull("memberId")||obj.getString("memberId").isEmpty() ? null : obj.getString("memberId");
		String	memberPassword = obj.isNull("memberPassword") ? null : obj.getString("memberPassword");
		String memberName = obj.isNull("memberName")||obj.getString("memberName").isEmpty() ? null : obj.getString("memberName");
		String email = obj.isNull("email")||obj.getString("email").isEmpty() ? null : obj.getString("email") ;
		Integer gender = obj.isNull("gender") ? null : obj.getInt("gender");
		String memberAddress  = obj.isNull("memberAddress")||obj.getString("memberAddress").isEmpty() ? null : obj.getString("memberAddress") ;
		Integer discountPointsLeft = obj.isNull("discountPointsLeft") ? null : obj.getInt("discountPointsLeft") ;
		String signUpDate = obj.isNull("signUpDate")||obj.getString("signUpDate").isEmpty() ? null : obj.getString("signUpDate") ;
		String finalLogInDate = obj.isNull("finalLogInDate")||obj.getString("finalLogInDate").isEmpty() ? null : obj.getString("finalLogInDate");
		String accessLevel = obj.isNull("accessLevel")||obj.getString("accessLevel").isEmpty() ? null : obj.getString("accessLevel");
		Integer couponQuantity = obj.isNull("couponQuantity") ? null : obj.getInt("couponQuantity");
		
		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
		//from member
		Root<MemberBean> root = criteria.from(MemberBean.class);
		//select count(*)
		criteria = criteria.select(builder.count(root));
		//where
		List<Predicate> predicates = new ArrayList<>();
		
		if(id!=null) {	predicates.add(builder.equal(root.get("id"), id));	}
		if(memberId!=null) {	predicates.add(builder.like(root.get("memberId"), "%"+memberId+"%"));	}		
		if(memberPassword!=null) {	predicates.add(builder.equal(root.get("memberPassword"), memberPassword));	}
		if(memberName!=null) {	predicates.add(builder.like(root.get("memberName"), "%"+memberName+"%"));	}
		if(email!=null) {	predicates.add(builder.like(root.get("email"), "%"+email+"%"));	}
		if(gender!=null) {	predicates.add(builder.equal(root.get("gender"), gender));	}
		if(memberAddress!=null) {	predicates.add(builder.like(root.get("memberAddress"), "%"+memberAddress+"%"));	}
		if(discountPointsLeft!=null) {	predicates.add(builder.equal(root.get("discountPointsLeft"), discountPointsLeft));	}
		if(signUpDate!=null) {	predicates.add(builder.equal(root.get("signUpDate"), signUpDate));	}
		if(finalLogInDate!=null) {	predicates.add(builder.equal(root.get("finalLogInDate"), finalLogInDate));	}
		if(accessLevel!=null) {	predicates.add(builder.like(root.get("accessLevel"), "%"+accessLevel+"%"));	}
		if(couponQuantity!=null) {	predicates.add(builder.equal(root.get("couponQuantity"), couponQuantity));	}
		
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[1]);
			criteria = criteria.where(array);
		}
		
		TypedQuery<Long> typedQuery = this.getSession().createQuery(criteria);
		return typedQuery.getSingleResult();
	}

}	
		
		
	
	
