package tw.com.tlcc.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tlcc.domain.MainDetailBean;
import tw.com.tlcc.repository.MainDetailRepository;

@Service
@Transactional
public class MainDetailRepositoryService {

	
	@Autowired
	private MainDetailRepository mainDetailRepository;
	
	
	public long count(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return mainDetailRepository.count(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	public List<MainDetailBean> find(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return mainDetailRepository.find(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MainDetailBean findById(String mainId) {
		Optional<MainDetailBean> optional = mainDetailRepository.findById(mainId);
		if(optional != null&& optional.isPresent()) {
			return optional.get();
		}
		return null;
		
	}



	


}
