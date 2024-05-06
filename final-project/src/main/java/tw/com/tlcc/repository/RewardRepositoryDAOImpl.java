package tw.com.tlcc.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.json.JSONObject;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.com.tlcc.domain.MemberBean;
import tw.com.tlcc.domain.RewardBean;

public class RewardRepositoryDAOImpl implements RewardRepositoryDAO{
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}
	@Override
	public List<RewardBean> find(JSONObject obj) {
		Integer id = obj.isNull("id") ? null : obj.getInt("id") ;
		String memberId = obj.isNull("memberId") ? null : obj.getString("memberId") ;
		Integer orderNumber = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber");
		String receivedDate = obj.isNull("receivedDate") ? null : obj.getString("receivedDate");
		Integer receivedPoints = obj.isNull("receivedPoints") ? null : obj.getInt("receivedPoints");
		String usedDate = obj.isNull("usedDate") ? null : obj.getString("usedDate") ;
		Integer usedPoints = obj.isNull("usedPoints") ? null : obj.getInt("usedPoints");
		Integer currentPoints = obj.isNull("currentPoints") ? null : obj.getInt("currentPoints");
		
		System.out.println(id);
//		
		String sort = obj.isNull("sort") ? "id" : obj.getString("sort");
		String order = obj.isNull("order") ? "desc" : obj.getString("order");
		Integer start = obj.isNull("start") ? 0 : obj.getInt("start");
		Integer rows = obj.isNull("rows") ? null : obj.getInt("rows");
		
//		select * from product
//		where id=? and name like ? and price > ? and make < ? and expire >= ?
//		order by id desc

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<RewardBean> criteria = builder.createQuery(RewardBean.class);
		
//		from product
		Root<RewardBean> root = criteria.from(RewardBean.class);
		
//		where
		List<Predicate> predicates = new ArrayList<>();
		
//		id=?
		if(id!=null) {
			predicates.add(builder.equal(root.get("id"), id));
		}
		
		if(memberId!=null && memberId.length()!=0) {
			predicates.add(builder.like(root.get("memberId"), "%"+memberId+"%"));
		}
		
//		name like ?
//		if(name!=null && name.length()!=0) {
//			predicates.add(builder.like(root.get("name"), "%"+name+"%"));
//		}
		
//		price > ?
//		if(price!=null) {
//			predicates.add(builder.greaterThan(root.get("price"), price));
//		}
		
//		make < ?
//		if(make!=null && make.length()!=0) {
//			java.util.Date date = DatetimeConverter.parse(make, "yyyy-MM-dd");
//			predicates.add(builder.lessThan(root.get("make"), date));
//		}
		
//		expire >= ?
//		if(expire!=null) {
//			predicates.add(builder.greaterThanOrEqualTo(root.get("expire"), expire));
//		}
		
//		where id=? and name like ? and price > ? and make < ? and expire >= ?
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[1]);
			criteria = criteria.where(array);
		}
		
//		order by id desc
//		if("desc".equals(order)) {
//			criteria = criteria.orderBy(builder.desc(root.get(sort)));
//		} else {
//			criteria = criteria.orderBy(builder.asc(root.get(sort)));
//		}
		
		TypedQuery<RewardBean> typedQuery = this.getSession().createQuery(criteria);
		if(rows!=null) {
			typedQuery = typedQuery.setFirstResult(start)
					.setMaxResults(rows);
		}
		
		List<RewardBean> result = typedQuery.getResultList();
		if(result!=null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
	}
		

	
	@Override
	public Long count(JSONObject obj) {
		Integer id = obj.isNull("id") ? null : obj.getInt("id") ;
		String memberId = obj.isNull("memberId") ? null : obj.getString("memberId") ;
		Integer orderNumber = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber");
		String receivedDate = obj.isNull("receivedDate") ? null : obj.getString("receivedDate");
		Integer receivedPoints = obj.isNull("receivedPoints") ? null : obj.getInt("receivedPoints");
		String usedDate = obj.isNull("usedDate") ? null : obj.getString("usedDate") ;
		Integer usedPoints = obj.isNull("usedPoints") ? null : obj.getInt("usedPoints");
		Integer currentPoints = obj.isNull("currentPoints") ? null : obj.getInt("currentPoints");
		
//		
		String sort = obj.isNull("sort") ? "id" : obj.getString("sort");
		String order = obj.isNull("order") ? "desc" : obj.getString("order");
		Integer start = obj.isNull("start") ? 0 : obj.getInt("start");
		Integer rows = obj.isNull("rows") ? null : obj.getInt("rows");

//		select count(*) from product
//		where id=? and name like ? and price > ? and make < ? and expire >= ?

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
//		from product
		Root<MemberBean> root = criteria.from(MemberBean.class);

//		select count(*)
		criteria = criteria.select(builder.count(root));
		
//		where
		List<Predicate> predicates = new ArrayList<>();
		
//		id=?
		if(memberId!=null) {
			predicates.add(builder.equal(root.get("id"), memberId));
		}
		
////		name like ?
//		if(name!=null && name.length()!=0) {
//			predicates.add(builder.like(root.get("name"), "%"+name+"%"));
//		}
//		
////		price > ?
//		if(price!=null) {
//			predicates.add(builder.greaterThan(root.get("price"), price));
//		}
//		
////		make < ?
//		if(make!=null && make.length()!=0) {
//			java.util.Date date = DatetimeConverter.parse(make, "yyyy-MM-dd");
//			predicates.add(builder.lessThan(root.get("make"), date));
//		}
//		
////		expire >= ?
//		if(expire!=null) {
//			predicates.add(builder.greaterThanOrEqualTo(root.get("expire"), expire));
//		}
		
//		where id=? and name like ? and price > ? and make < ? and expire >= ?
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[1]);
			criteria = criteria.where(array);
		}
		
		TypedQuery<Long> typedQuery = this.getSession().createQuery(criteria);
		return typedQuery.getSingleResult();
	}	
}


