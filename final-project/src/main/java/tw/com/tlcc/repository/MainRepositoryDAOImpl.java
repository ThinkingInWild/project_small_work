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
import tw.com.tlcc.domain.MainBean;


public class MainRepositoryDAOImpl implements MainRepositoryDAO{

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}
	
	@Override
	public List<MainBean> find(JSONObject obj){
		String mainId = obj.isNull("mainId") ? null : obj.getString("mainId");
		String mainName = obj.isNull("mainName") ? null : obj.getString("mainName");
		Integer mainPrice = obj.isNull("mainPrice") ? null : obj.getInt("mainPrice");
		Integer extraMainPrice = obj.isNull("extraMainPrice") ? null : obj.getInt("extraMainPrice");

		String sort = obj.isNull("sort") ? "id" : obj.getString("sort");
		String order = obj.isNull("order") ? "desc" : obj.getString("order");
		Integer start = obj.isNull("start") ? 0 : obj.getInt("start");
		Integer rows = obj.isNull("rows") ? null : obj.getInt("rows");
	
//		select count(*) from product
//		where id=? and name like ? and price > ? and make < ? and expire >= ?

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<MainBean> criteria = builder.createQuery(MainBean.class);
		
// 		from Member
		Root<MainBean> root = criteria.from(MainBean.class);
		
//		where
		List<Predicate> predicates = new ArrayList<>();

//		mainId=?
		if(mainId!=null) {
			predicates.add(builder.equal(root.get("mainId"), mainId));
		}

//		where id=? and name like ? and price > ? and make < ? and expire >= ?
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[1]);
			criteria = criteria.where(array);
		}

		
		TypedQuery<MainBean> typedQuery = this.getSession().createQuery(criteria);
		if(rows!=null) {
			typedQuery = typedQuery.setFirstResult(start)
					.setMaxResults(rows);
		}
		
		List<MainBean> result = typedQuery.getResultList();
		if(result!=null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
	}
	
	@Override
	public Long count(JSONObject obj) {
		String mainId = obj.isNull("mainId") ? null : obj.getString("mainId");
		String mainName = obj.isNull("mainName") ? null : obj.getString("mainName");
		Integer mainPrice = obj.isNull("mainPrice") ? null : obj.getInt("mainPrice");
		Integer extraMainPrice = obj.isNull("extraMainPrice") ? null : obj.getInt("extraMainPrice");
		
		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
//		from Purchase
		Root<MainBean> root = criteria.from(MainBean.class);

//		select count(*)
		criteria = criteria.select(builder.count(root));
		
//		where
		List<Predicate> predicates = new ArrayList<>();
		
//		id=?
		if(mainId!=null) {
			predicates.add(builder.equal(root.get("mainId"), mainId));
		}
		TypedQuery<Long> typedQuery = this.getSession().createQuery(criteria);
		return typedQuery.getSingleResult();
		
	}
	
}
