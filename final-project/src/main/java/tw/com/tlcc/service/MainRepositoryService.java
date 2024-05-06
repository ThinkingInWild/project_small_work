package tw.com.tlcc.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tlcc.domain.MainBean;
import tw.com.tlcc.domain.PurchaseBean;
import tw.com.tlcc.repository.MainRepository;

@Service
@Transactional
public class MainRepositoryService {

	
	@Autowired
	private MainRepository mainRepository;
	
	public MainBean create(String json) {
		
		try {
			JSONObject obj = new JSONObject(json);
			String mainId = obj.isNull("mainId") ? null : obj.getString("mainId");
			String mainName = obj.isNull("mainName") ? null : obj.getString("mainName");
			Integer mainPrice = obj.isNull("mainPrice") ? null : obj.getInt("mainPrice");
			Integer extraMainPrice = obj.isNull("extraMainPrice") ? null : obj.getInt("extraMainPrice");

			MainBean insert = new MainBean();
			insert.setMainId(mainId);
			insert.setMainName(mainName);
			insert.setMainPrice(mainPrice);
			insert.setExtraMainPrice(extraMainPrice);
			
			if(!mainRepository.existsById(mainId)){
				return mainRepository.save(insert);
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public long count(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return mainRepository.count(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<MainBean> find(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return mainRepository.find(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MainBean findById(String mainId) {
		Optional<MainBean> optional = mainRepository.findById(mainId);
		if(optional != null&& optional.isPresent()) {
			return optional.get();
		}
		return null;
		
	}
	
	public boolean exists(String mainId) {
		return mainRepository.existsById(mainId);
	}
	
	public MainBean modify(String json) {
		
		try {
			JSONObject obj = new JSONObject(json);
			String mainId = obj.isNull("mainId") ? null : obj.getString("mainId");
			String mainName = obj.isNull("mainName") ? null : obj.getString("mainName");
			Integer mainPrice = obj.isNull("mainPrice") ? null : obj.getInt("mainPrice");
			Integer extraMainPrice = obj.isNull("extraMainPrice") ? null : obj.getInt("extraMainPrice");

			Optional<MainBean> optional = mainRepository.findById(mainId);
			if(optional != null && optional.isPresent()) {
				
				MainBean update = optional.get();
				update.setMainId(mainId);
				update.setMainName(mainName);
				update.setMainPrice(mainPrice);
				update.setExtraMainPrice(extraMainPrice);
				
				return mainRepository.save(update);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean remove(String mainId) {
		try {
			if(mainRepository.existsById(mainId)) {
				mainRepository.deleteById(mainId);
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}
	
}
