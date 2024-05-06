package tw.com.tlcc.repository;

import java.util.List;

import org.json.JSONObject;

import tw.com.tlcc.domain.PurchaseBean;

public interface PurchaseRepositoryDAO {

	List<PurchaseBean> find(JSONObject obj);

	Long count(JSONObject obj);
}
