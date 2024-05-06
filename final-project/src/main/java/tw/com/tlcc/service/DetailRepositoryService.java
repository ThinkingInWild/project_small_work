package tw.com.tlcc.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tlcc.domain.DetailBean;
import tw.com.tlcc.repository.DetailRepository;

@Service
@Transactional(rollbackFor = { Exception.class })
public class DetailRepositoryService {

	@Autowired
	private DetailRepository detailRepository;

	public DetailBean create(String json) { // 新增
		
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id") ;		
			Integer orderNumber = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber");
			String main = obj.isNull("main") ? null : obj.getString("main");
			String side1 = obj.isNull("side1") ? null : obj.getString("side1");
			String side2 = obj.isNull("side2") ? null : obj.getString("side2");
			String side3 = obj.isNull("side3") ? null : obj.getString("side3");
			String side4 = obj.isNull("side4") ? null : obj.getString("side4");
			String extraMain = obj.isNull("extraMain") ? null : obj.getString("extraMain");
			Integer detailTotal = obj.isNull("detailTotal") ? null : obj.getInt("detailTotal");
			
			System.out.println(id);
			
			DetailBean insert = new DetailBean();
			
			insert.setId(id);
			insert.setOrderNumber(orderNumber);
			insert.setMain(main);
			insert.setSide1(side1);
			insert.setSide2(side2);
			insert.setSide3(side3);
			insert.setSide4(side4);
			insert.setExtraMain(extraMain);
			insert.setDetailTotal(detailTotal);
			
			System.out.println(id);
			
			if (!detailRepository.existsById(id)) {
				System.out.println(id);
				System.out.println(insert);
				return detailRepository.save(insert);
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean remove(Integer id) { // 刪除
		try {
			if (detailRepository.existsById(id)) {
				System.out.println(id);
				detailRepository.deleteById(id);
				
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public DetailBean modify(String json) {											//修改
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id") ;		
			Integer orderNumber = obj.isNull("orderNumber") ? 0 : obj.getInt("orderNumber");	
			String main = obj.isNull("main") ? null : obj.getString("main");
			String side1 = obj.isNull("side1") ? null : obj.getString("side1");
			String side2 = obj.isNull("side2") ? null : obj.getString("side2");
			String side3 = obj.isNull("side3") ? null : obj.getString("side3");
			String side4 = obj.isNull("side4") ? null : obj.getString("side4");
			String extraMain = obj.isNull("extraMain") ? null : obj.getString("extraMain");
			Integer detailTotal = obj.isNull("detailTotal") ? 0 : obj.getInt("detailTotal");
			
			Optional<DetailBean> optional = detailRepository.findById(id);
			if(optional != null && optional.isPresent()) {
				
				DetailBean update = optional.get();
				update.setId(id);
				update.setOrderNumber(orderNumber);
				update.setMain(main);
				update.setSide1(side1);
				update.setSide2(side2);
				update.setSide3(side3);
				update.setSide4(side4);
				update.setExtraMain(extraMain);
				update.setDetailTotal(detailTotal);
				return detailRepository.save(update);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DetailBean> find(String json) {											//查詢全部
		try {
			JSONObject obj = new JSONObject(json);
			return detailRepository.find(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DetailBean findById(Integer id) {											//查詢 by Id
		Optional<DetailBean> optional = detailRepository.findById(id);
		if(optional != null&& optional.isPresent()) {
			return optional.get();
		}
		return null;
		
	}
	
	public long count(String json) {													//計數
		try {
			JSONObject obj = new JSONObject(json);
			return detailRepository.count(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean exists(Integer id) {													//查詢資料是否有這筆資料
		return detailRepository.existsById(id);
		
		
	}
	
	
	
}
