package tw.com.tlcc.repository;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import tw.com.tlcc.domain.InventoryBean;

@Repository
public interface InventoryRepositoryDAO {
	List<InventoryBean> find(JSONObject obj);
	
	InventoryBean update(InventoryBean bean);
	
	Long count(JSONObject obj);
	
	
}
