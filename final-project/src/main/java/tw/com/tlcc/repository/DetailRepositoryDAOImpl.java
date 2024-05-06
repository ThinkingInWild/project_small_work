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
import tw.com.tlcc.domain.DetailBean;
import tw.com.tlcc.domain.MemberBean;

public class DetailRepositoryDAOImpl implements DetailRepositoryDAO{

	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public List<DetailBean> find(JSONObject obj) {
		Integer id = obj.isNull("id") ? null : obj.getInt("id") ;		
		Integer orderNumber = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber") ;
		String main = obj.isNull("main") ? null : obj.getString("main");
		String side1 = obj.isNull("side1") ? null : obj.getString("side1");
		String side2 = obj.isNull("side2") ? null : obj.getString("side2");
		String side3 = obj.isNull("side3") ? null : obj.getString("side3") ;
		String side4 = obj.isNull("side4") ? null : obj.getString("side4");
		String extra_main  = obj.isNull("extra_main") ? null : obj.getString("extra_main") ;
		Integer detail_total = obj.isNull("detail_total") ? null : obj.getInt("detail_total") ;
		
		
		String sort = obj.isNull("sort") ? "id" : obj.getString("sort");
		String order = obj.isNull("order") ? "desc" : obj.getString("order");
		Integer start = obj.isNull("start") ? 0 : obj.getInt("start");
		Integer rows = obj.isNull("rows") ? null : obj.getInt("rows");
		
//		select * from product
//		where id=? and name like ? and price > ? and make < ? and expire >= ?
//		order by id desc

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<DetailBean> criteria = builder.createQuery(DetailBean.class);
		
//		from product
		Root<DetailBean> root = criteria.from(DetailBean.class);
		
//		where
		List<Predicate> predicates = new ArrayList<>();
		
//		id=?
		if(id!=null) {
			predicates.add(builder.equal(root.get("id"), id));
		}
		
		if(orderNumber!=null) {
			predicates.add(builder.equal(root.get("orderNumber"), orderNumber));
		}
//		
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
//		
//		where id=? and name like ? and price > ? and make < ? and expire >= ?
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[1]);
			criteria = criteria.where(array);
		}
//		
////		order by id desc
//		if("desc".equals(order)) {
//			criteria = criteria.orderBy(builder.desc(root.get(sort)));
//		} else {
//			criteria = criteria.orderBy(builder.asc(root.get(sort)));
//		}
//		
		TypedQuery<DetailBean> typedQuery = this.getSession().createQuery(criteria);
		if(rows!=null) {
			typedQuery = typedQuery.setFirstResult(start)
					.setMaxResults(rows);
		}
		
		List<DetailBean> result = typedQuery.getResultList();
		if(result!=null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
//	
	} 
	
	@Override
	public Long count(JSONObject obj) {
		Integer id = obj.isNull("id") ? null : obj.getInt("id") ;		
		Integer orderNumber = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber") ;
		String main = obj.isNull("main") ? null : obj.getString("main");
		String side1 = obj.isNull("side1") ? null : obj.getString("side1");
		String side2 = obj.isNull("side2") ? null : obj.getString("side2");
		String side3 = obj.isNull("side3") ? null : obj.getString("side3") ;
		String side4 = obj.isNull("side4") ? null : obj.getString("side4");
		String extraMain  = obj.isNull("extraMain") ? null : obj.getString("extraMain") ;
		Integer detailTotal = obj.isNull("detailTotal") ? null : obj.getInt("detailTotal");
		
//		select count(*) from product
//		where id=? and name like ? and price > ? and make < ? and expire >= ?

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
//		from product
		Root<DetailBean> root = criteria.from(DetailBean.class);

//		select count(*)
		criteria = criteria.select(builder.count(root));
		
//		where
		List<Predicate> predicates = new ArrayList<>();
		
//		id=?
		if(orderNumber!=null) {
			predicates.add(builder.equal(root.get("orderNumber"), orderNumber));
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
