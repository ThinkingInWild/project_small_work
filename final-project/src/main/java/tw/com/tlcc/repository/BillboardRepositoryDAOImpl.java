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
import tw.com.tlcc.domain.BillboardBean;

@Repository
public class BillboardRepositoryDAOImpl implements BillboardRepositoryDAO {
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public List<BillboardBean> find(JSONObject obj) {
		
		Integer id = obj.isNull("id")||obj.getInt("id")==0 ? null : obj.getInt("id");
		String alias = obj.isNull("alias") ? null : obj.getString("alias");
		String content = obj.isNull("content") ? null : obj.getString("content");
		String messageTime = obj.isNull("messageTime") ? null : obj.getString("messageTime");
		Integer pushNumber = obj.isNull("pushNumber") ? null : obj.getInt("pushNumber") ;
		Integer youSuckNumber = obj.isNull("youSuckNumber") ? null : obj.getInt("youSuckNumber") ;
		Integer viewCount = obj.isNull("viewCount") ? null : obj.getInt("viewCount") ;
		
		String sort = obj.isNull("sort") ? "id" : obj.getString("sort");
		String order = obj.isNull("order") ? "desc" : obj.getString("order");
		Integer start = obj.isNull("start") ? 0 : obj.getInt("start");
		Integer rows = obj.isNull("rows") ? null : obj.getInt("rows");
		
		//建query
		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<BillboardBean> criteria = builder.createQuery(BillboardBean.class);
		
		//From billboard
		Root<BillboardBean> root = criteria.from(BillboardBean.class);
		
		//Predicate(查詢條件) = 添加where後面的表達式。
		List<Predicate> predicates = new ArrayList<>();
		
		if(id!=null) {	predicates.add(builder.equal(root.get("id"), id));	}
		if(alias!=null) {	predicates.add(builder.equal(root.get("alias"), alias));	}		
		if(content!=null) {	predicates.add(builder.equal(root.get("content"), content));	}
		if(messageTime!=null) {	predicates.add(builder.equal(root.get("messageTime"), messageTime));	}
		if(pushNumber!=null) {	predicates.add(builder.equal(root.get("pushNumber"), pushNumber));	}
		if(youSuckNumber!=null) {	predicates.add(builder.equal(root.get("youSuckNumber"), youSuckNumber));	}
		if(viewCount!=null) {	predicates.add(builder.equal(root.get("viewCount"), viewCount));	}
		
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[1]);
			criteria = criteria.where(array); 
		}
		
		if("desc".equals(order)) {
			criteria = criteria.orderBy(builder.desc(root.get(sort)));
		} else {
			criteria = criteria.orderBy(builder.asc(root.get(sort)));
		}
		
		TypedQuery<BillboardBean> typedQuery = this.getSession().createQuery(criteria);
		if(rows!=null) {
			typedQuery = typedQuery.setFirstResult(start)
					.setMaxResults(rows);
		}
		
		List<BillboardBean> result = typedQuery.getResultList();
		if(result!=null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
		
	}
	

	@Override
	public Long count(JSONObject obj) {
		
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String alias = obj.isNull("alias") ? null : obj.getString("alias");
		String content = obj.isNull("content") ? null : obj.getString("content");
		String messageTime = obj.isNull("messageTime") ? null : obj.getString("messageTime");
		Integer pushNumber = obj.isNull("pushNumber") ? null : obj.getInt("pushNumber") ;
		Integer youSuckNumber = obj.isNull("youSuckNumber") ? null : obj.getInt("youSuckNumber") ;
		Integer viewCount = obj.isNull("viewCount") ? null : obj.getInt("viewCount") ;
				
		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
		//from billboard
		Root<BillboardBean> root = criteria.from(BillboardBean.class);
		//select count(*)
		criteria = criteria.select(builder.count(root));
		//where
		List<Predicate> predicates = new ArrayList<>();
		
		if(id!=null) {	predicates.add(builder.equal(root.get("id"), id));	}
		if(alias!=null) {	predicates.add(builder.equal(root.get("alias"), alias));	}		
		if(content!=null) {	predicates.add(builder.equal(root.get("content"), content));	}
		if(messageTime!=null) {	predicates.add(builder.equal(root.get("messageTime"), messageTime));	}
		if(pushNumber!=null) {	predicates.add(builder.equal(root.get("pushNumber"), pushNumber));	}
		if(youSuckNumber!=null) {	predicates.add(builder.equal(root.get("youSuckNumber"), youSuckNumber));	}
		if(viewCount!=null) {	predicates.add(builder.equal(root.get("viewCount"), viewCount));	}
		
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[1]);
			criteria = criteria.where(array);
		}
		
		TypedQuery<Long> typedQuery = this.getSession().createQuery(criteria);
		return typedQuery.getSingleResult();
	
	
	}

}
