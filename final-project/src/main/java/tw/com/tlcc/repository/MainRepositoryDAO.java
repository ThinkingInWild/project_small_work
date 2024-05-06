package tw.com.tlcc.repository;

import java.util.List;

import org.json.JSONObject;

import tw.com.tlcc.domain.MainBean;

public interface MainRepositoryDAO {

	List<MainBean> find(JSONObject obj);

	Long count(JSONObject obj);
}
