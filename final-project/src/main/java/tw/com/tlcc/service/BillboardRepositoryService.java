package tw.com.tlcc.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tlcc.domain.BillboardBean;
import tw.com.tlcc.repository.BillboardRepository;
import tw.com.tlcc.util.DatetimeConverter;


@Service
@Transactional
public class BillboardRepositoryService {
	
	@Autowired
	private BillboardRepository billboardRepository;
	
	//新增
	public BillboardBean create(String json) {						
		try{ 
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String alias = obj.isNull("alias") ? null : obj.getString("alias");
			String content = obj.isNull("content") ? null : obj.getString("content");
			String messageTime = obj.isNull("messageTime") ? null : obj.getString("messageTime");
			Integer pushNumber = obj.isNull("pushNumber") ? null : obj.getInt("pushNumber") ;
			Integer youSuckNumber = obj.isNull("youSuckNumber") ? null : obj.getInt("youSuckNumber") ;
			Integer viewCount = obj.isNull("viewCount") ? null : obj.getInt("viewCount") ;
			
			BillboardBean insert = new BillboardBean();
			insert.setId(id);
			insert.setAlias(alias);
			insert.setContent(content);
			
			SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			Date inputDate = inputDateFormat.parse(messageTime);
			SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String outputDate = outputDateFormat.format(inputDate);	
            Date formattedDate = outputDateFormat.parse(outputDate);
            insert.setMessageTime(formattedDate);
//            insert.setMessageTime(DatetimeConverter.parse(inputDate, "yyyy-MM-dd HH:mm"));
			
			insert.setPushNumber(pushNumber);
			insert.setYouSuckNumber(youSuckNumber);
			insert.setViewCount(viewCount);
			
			if(!billboardRepository.existsById(id)){
				return billboardRepository.save(insert);
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//修改
	public BillboardBean modify(String json) {											
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String alias = obj.isNull("alias") ? null : obj.getString("alias");
			String content = obj.isNull("content") ? null : obj.getString("content");
			String messageTime = obj.isNull("messageTime") ? null : obj.getString("messageTime");
			Integer pushNumber = obj.isNull("pushNumber") ? null : obj.getInt("pushNumber") ;
			Integer youSuckNumber = obj.isNull("youSuckNumber") ? null : obj.getInt("youSuckNumber") ;
			Integer viewCount = obj.isNull("viewCount") ? null : obj.getInt("viewCount") ;
			

			Optional<BillboardBean> optional = billboardRepository.findById(id);
			if(optional != null && optional.isPresent()) {
				
				BillboardBean update = optional.get();
				update.setId(id);
				update.setAlias(alias);
				update.setContent(content);
				update.setMessageTime(DatetimeConverter.parse(messageTime, "yyyy-MM-dd HH:mm"));
				update.setPushNumber(pushNumber);
				update.setYouSuckNumber(youSuckNumber);
				update.setViewCount(viewCount);
				return billboardRepository.save(update);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查詢資料是否有這筆資料
	public boolean exists(Integer id) {					
		return billboardRepository.existsById(id);
	} 
	
	//刪除
	public boolean remove(Integer id) {					
		try {
			if(billboardRepository.existsById(id)) {
				billboardRepository.deleteById(id);
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	//查全部 JpaRepository原生
	public List<BillboardBean> findAll(){ 					
		return billboardRepository.findAll();
	}
	
	//查詢byId
	public BillboardBean findById(Integer id) {			
		Optional<BillboardBean> optional = billboardRepository.findById(id);
		if(optional != null&& optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	//條件式查詢
	public List<BillboardBean> find(String json) {			
		try {
			JSONObject obj = new JSONObject(json);
			return billboardRepository.find(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//計數
	public long count(String json) {					
		try {
			JSONObject obj = new JSONObject(json);
			return billboardRepository.count(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
}
