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
import tw.com.tlcc.domain.PurchaseBean;

public class PurchaseRepositoryDAOImpl implements PurchaseRepositoryDAO{

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}
	
	@Override
	public List<PurchaseBean> find(JSONObject obj){
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		Integer orderNumber = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber");
		String memberId = obj.isNull("memberId") ? null : obj.getString("memberId");
		String orderAddress = obj.isNull("orderAddress") ? null : obj.getString("orderAddress");
		String orderDate = obj.isNull("orderDate") ? null : obj.getString("orderDate");
		String pickupDate = obj.isNull("pickupDate") ? null : obj.getString("pickupDate");
		Integer orderTotal = obj.isNull("orderTotal") ? null : obj.getInt("orderTotal");
		Integer chicken = obj.isNull("chicken") ? null : obj.getInt("chicken");
		Integer pork = obj.isNull("pork") ? null : obj.getInt("pork");
		Integer beef = obj.isNull("beef") ? null : obj.getInt("beef");
		Integer lamb = obj.isNull("lamb") ? null : obj.getInt("lamb");
		Integer fish = obj.isNull("fish") ? null : obj.getInt("fish");
		Integer extraChicken = obj.isNull("extraChicken") ? null : obj.getInt("extraChicken");
		Integer extraPork = obj.isNull("extraPork") ? null : obj.getInt("extraPork");
		Integer extraBeef = obj.isNull("extraBeef") ? null : obj.getInt("extraBeef");
		Integer extraLamb = obj.isNull("extraLamb") ? null : obj.getInt("extraLamb");
		Integer extraFish = obj.isNull("extraFish") ? null : obj.getInt("extraFish");

		String sort = obj.isNull("sort") ? "id" : obj.getString("sort");
		String order = obj.isNull("order") ? "desc" : obj.getString("order");
		Integer start = obj.isNull("start") ? 0 : obj.getInt("start");
		Integer rows = obj.isNull("rows") ? null : obj.getInt("rows");
		
//		select count(*) from product
//		where id=? and name like ? and price > ? and make < ? and expire >= ?

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<PurchaseBean> criteria = builder.createQuery(PurchaseBean.class);
		
// 		from Member
		Root<PurchaseBean> root = criteria.from(PurchaseBean.class);
		
//		where
		List<Predicate> predicates = new ArrayList<>();

//		id=?
		if(id!=null) {
			predicates.add(builder.equal(root.get("id"), id));
		}
		
//		memberId = ? 
		if(memberId!=null) {
			predicates.add(builder.equal(root.get("memberId"), memberId));
		}
	
//		orderNumber = ? 
		if(orderNumber!=null) {
			predicates.add(builder.equal(root.get("orderNumber"), orderNumber));
		}
		
//		where id=? and name like ? and price > ? and make < ? and expire >= ?
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[1]);
			criteria = criteria.where(array);
		}

//		order by id desc
		if("desc".equals(order)) {
			criteria = criteria.orderBy(builder.desc(root.get(sort)));
		} else {
			criteria = criteria.orderBy(builder.asc(root.get(sort)));
		}
		
		TypedQuery<PurchaseBean> typedQuery = this.getSession().createQuery(criteria);
		if(rows!=null) {
			typedQuery = typedQuery.setFirstResult(start)
					.setMaxResults(rows);
		}
		
		List<PurchaseBean> result = typedQuery.getResultList();
		if(result!=null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
		
	}
	
	@Override
	public Long count(JSONObject obj) {
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		Integer orderNumber = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber");
		String memberId = obj.isNull("memberId") ? null : obj.getString("memberId");
		String orderAddress = obj.isNull("orderAddress") ? null : obj.getString("orderAddress");
		String orderDate = obj.isNull("orderDate") ? null : obj.getString("orderDate");
		String pickupDate = obj.isNull("pickupDate") ? null : obj.getString("pickupDate");
		Integer orderTotal = obj.isNull("orderTotal") ? null : obj.getInt("orderTotal");
		Integer chicken = obj.isNull("chicken") ? null : obj.getInt("chicken");
		Integer pork = obj.isNull("pork") ? null : obj.getInt("pork");
		Integer beef = obj.isNull("beef") ? null : obj.getInt("beef");
		Integer lamb = obj.isNull("lamb") ? null : obj.getInt("lamb");
		Integer fish = obj.isNull("fish") ? null : obj.getInt("fish");
		Integer extraChicken = obj.isNull("extraChicken") ? null : obj.getInt("extraChicken");
		Integer extraPork = obj.isNull("extraPork") ? null : obj.getInt("extraPork");
		Integer extraBeef = obj.isNull("extraBeef") ? null : obj.getInt("extraBeef");
		Integer extraLamb = obj.isNull("extraLamb") ? null : obj.getInt("extraLamb");
		Integer extraFish = obj.isNull("extraFish") ? null : obj.getInt("extraFish");
		
		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
//		from Purchase
		Root<PurchaseBean> root = criteria.from(PurchaseBean.class);

//		select count(*)
		criteria = criteria.select(builder.count(root));
		
//		where
		List<Predicate> predicates = new ArrayList<>();
		
//		id=?
		if(id!=null) {
			predicates.add(builder.equal(root.get("id"), id));
		}
		TypedQuery<Long> typedQuery = this.getSession().createQuery(criteria);
		return typedQuery.getSingleResult();
	}
}
