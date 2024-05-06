package tw.com.tlcc.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.tlcc.domain.ViewDetailMainBean;
import tw.com.tlcc.repository.ViewDetailMainDAO;

@Service
@Transactional(rollbackFor = {Exception.class})
public class ViewDetailMainService {
	
	@Autowired
	private ViewDetailMainDAO viewDetailMainDAO;
	
	public List<ViewDetailMainBean> find(String json) {											//查詢全部
		try {
			JSONObject obj = new JSONObject(json);
			return viewDetailMainDAO.find(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public long count(String json) {													//計數
		try {
			JSONObject obj = new JSONObject(json);
			return viewDetailMainDAO.count(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ViewDetailMainBean findById(Integer id) {
		return viewDetailMainDAO.select(id);
	}
	
	public boolean exists(Integer id) {
		return viewDetailMainDAO.select(id) != null;
	}
	
	
	
	
}
