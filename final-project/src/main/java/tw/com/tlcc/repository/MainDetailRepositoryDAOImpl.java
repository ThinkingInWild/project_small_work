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
import tw.com.tlcc.domain.MainDetailBean;

public class MainDetailRepositoryDAOImpl implements MainDetailRepositoryDAO{

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}
	
	
	@Override
	public List<MainDetailBean> find(JSONObject obj){
		
		//Main
		String mainId = obj.isNull("mainId") ? null : obj.getString("mainId");
		String mainName = obj.isNull("mainName") ? null : obj.getString("mainName");
		Integer mainPrice = obj.isNull("mainPrice") ? null : obj.getInt("mainPrice");
		Integer extraMainPrice = obj.isNull("extraMainPrice") ? null : obj.getInt("extraMainPrice");
		
		//Detail
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

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<MainDetailBean> criteria = builder.createQuery(MainDetailBean.class);

		// from main_detail
		Root<MainDetailBean> root = criteria.from(MainDetailBean.class);

		//where
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

		
		TypedQuery<MainDetailBean> typedQuery = this.getSession().createQuery(criteria);
		if(rows!=null) {
			typedQuery = typedQuery.setFirstResult(start)
					.setMaxResults(rows);
		}
		
		List<MainDetailBean> result = typedQuery.getResultList();
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
		
		Integer orderNumber = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber") ;
		String main = obj.isNull("main") ? null : obj.getString("main");
		String side1 = obj.isNull("side1") ? null : obj.getString("side1");
		String side2 = obj.isNull("side2") ? null : obj.getString("side2");
		String side3 = obj.isNull("side3") ? null : obj.getString("side3") ;
		String side4 = obj.isNull("side4") ? null : obj.getString("side4");
		String extraMain  = obj.isNull("extraMain") ? null : obj.getString("extraMain") ;
		Integer detailTotal = obj.isNull("detailTotal") ? null : obj.getInt("detailTotal");

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
//		from Purchase
		Root<MainDetailBean> root = criteria.from(MainDetailBean.class);

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
