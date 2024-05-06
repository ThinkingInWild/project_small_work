package tw.com.tlcc.repository;

import java.util.List;

import org.json.JSONObject;

import tw.com.tlcc.domain.RewardBean;

public interface RewardRepositoryDAO {

List<RewardBean> find(JSONObject obj);
	
	Long count (JSONObject obj);
	
}
