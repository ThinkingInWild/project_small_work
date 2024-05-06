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
import tw.com.tlcc.domain.InventoryBean;
import tw.com.tlcc.util.DatetimeConverter;

@Repository
public class InventoryRepositoryDAOImpl implements InventoryRepositoryDAO {
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public List<InventoryBean> find(JSONObject obj) {
		Integer id = obj.isNull("id") ? null : obj.getInt("id") ;
		String inventoryDate = obj.isNull("inventoryDate") ? null : obj.getString("inventoryDate");
		Integer pork = obj.isNull("pork") ? null : obj.getInt("pork");
		Integer beef = obj.isNull("beef") ? null : obj.getInt("beef");
		Integer lamb = obj.isNull("lamb") ? null : obj.getInt("lamb") ;
		Integer fish = obj.isNull("fish") ? null : obj.getInt("fish");
		Integer chicken  = obj.isNull("chicken") ? null : obj.getInt("chicken") ;
		
		String sort = obj.isNull("sort") ? "id" : obj.getString("sort");
		String order = obj.isNull("order") ? "desc" : obj.getString("order");
		Integer start = obj.isNull("start") ? 0 : obj.getInt("start");
		Integer rows = obj.isNull("rows") ? null : obj.getInt("rows");
		
//		select * from product
//		where id=? and name like ? and price > ? and make < ? and expire >= ?
//		order by id desc

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<InventoryBean> criteria = builder.createQuery(InventoryBean.class);
		
//		from product
		Root<InventoryBean> root = criteria.from(InventoryBean.class);
		
//		where
		List<Predicate> predicates = new ArrayList<>();
		
//		id=?
		if(id!=null) {
			predicates.add(builder.equal(root.get("id"), id));
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
////		inventoryDate < ?
		if(inventoryDate!=null && inventoryDate.length()!=0) {
			java.util.Date date = DatetimeConverter.parse(inventoryDate, "yyyy-MM-dd");
			predicates.add(builder.lessThanOrEqualTo(root.get("inventoryDate"), date));
		}
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
		TypedQuery<InventoryBean> typedQuery = this.getSession().createQuery(criteria);
		if(rows!=null) {
			typedQuery = typedQuery.setFirstResult(start)
					.setMaxResults(rows);
		}
		
		List<InventoryBean> result = typedQuery.getResultList();
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
		String inventoryDate = obj.isNull("inventoryDate") ? null : obj.getString("inventoryDate");
		Integer pork = obj.isNull("pork") ? null : obj.getInt("pork");
		Integer beef = obj.isNull("beef") ? null : obj.getInt("beef");
		Integer lamb = obj.isNull("lamb") ? null : obj.getInt("lamb") ;
		Integer fish = obj.isNull("fish") ? null : obj.getInt("fish");
		Integer chicken  = obj.isNull("chicken") ? null : obj.getInt("chicken") ;

//		select count(*) from product
//		where id=? and name like ? and price > ? and make < ? and expire >= ?

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
//		from product
		Root<InventoryBean> root = criteria.from(InventoryBean.class);

//		select count(*)
		criteria = criteria.select(builder.count(root));
		
//		where
		List<Predicate> predicates = new ArrayList<>();
		
//		id=?
		if(id!=null) {
			predicates.add(builder.equal(root.get("id"), id));
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
//		inventoryDate = ?
		if(inventoryDate !=null && inventoryDate.length()!=0) {
			java.util.Date date = DatetimeConverter.parse(inventoryDate, "yyyy-MM-dd");
			predicates.add(builder.lessThanOrEqualTo(root.get("inventoryDate"), date));
		}
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
	
	public InventoryBean update(InventoryBean bean) {
		if(bean!=null && bean.getId()!=null) {
			InventoryBean temp = this.getSession().get(InventoryBean.class, bean.getId());
			if(temp!=null) {
				return (InventoryBean) this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	
}
