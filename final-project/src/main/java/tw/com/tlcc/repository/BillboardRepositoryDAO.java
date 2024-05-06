package tw.com.tlcc.repository;

import java.util.List;

import org.json.JSONObject;

import tw.com.tlcc.domain.BillboardBean;

public interface BillboardRepositoryDAO {
	List<BillboardBean> find(JSONObject obj);
	Long count(JSONObject obj);

}
