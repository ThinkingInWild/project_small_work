package tw.com.tlcc.repository;

import java.util.List;

import org.json.JSONObject;

import tw.com.tlcc.domain.MainDetailBean;

public interface MainDetailRepositoryDAO {

	List<MainDetailBean> find(JSONObject obj);
	
	Long count(JSONObject obj);

}
