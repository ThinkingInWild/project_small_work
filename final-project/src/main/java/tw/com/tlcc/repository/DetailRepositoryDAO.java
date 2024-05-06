package tw.com.tlcc.repository;

import java.util.List;

import org.json.JSONObject;

import tw.com.tlcc.domain.DetailBean;

public interface DetailRepositoryDAO {

	List<DetailBean> find(JSONObject obj);

	Long count(JSONObject obj);
	
}
