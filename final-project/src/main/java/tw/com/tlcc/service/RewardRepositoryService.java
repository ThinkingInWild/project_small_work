package tw.com.tlcc.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import tw.com.tlcc.domain.RewardBean;
import tw.com.tlcc.repository.MemberRepository;
import tw.com.tlcc.repository.RewardRepository;
import tw.com.tlcc.util.DatetimeConverter;

@Service
@Transactional(rollbackFor= {Exception.class})
public class RewardRepositoryService {
	@Autowired
	private  RewardRepository rewardRepository;
	
	public RewardBean create(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id") ;
			String memberId = obj.isNull("memberId") ? null : obj.getString("memberId");
			Integer orderNumber = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber");
			String receivedDate = obj.isNull("receivedDate") ? null : obj.getString("receivedDate");
			Integer receivedPoints = obj.isNull("receivedPoints") ? null : obj.getInt("receivedPoints");
			String usedDate = obj.isNull("usedDate") ? null : obj.getString("usedDate");
			Integer usedPoints = obj.isNull("usedPoints") ? null : obj.getInt("usedPoints");
			Integer currentPoints = obj.isNull("currentPoints") ? null : obj.getInt("currentPoints");
			
			RewardBean insert = new RewardBean();
			insert.setId(id);
			insert.setMemberId(memberId);
			insert.setOrderNumber(orderNumber);
			insert.setReceivedDate(DatetimeConverter.parse(receivedDate, "yyyy-MM-dd"));
			insert.setReceivedPoints(receivedPoints);
			insert.setUsedDate(DatetimeConverter.parse(usedDate, "yyyy-MM-dd"));
			insert.setUsedPoints(usedPoints);
			insert.setCurrentPoints(currentPoints);
			
			if(!rewardRepository.existsById(id)){
				return rewardRepository.save(insert);
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
		
		
		
		public long count(String json) {
			try {
				JSONObject obj = new JSONObject(json);
				return rewardRepository.count(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		
		public List<RewardBean> find(String json) {
			try {
				JSONObject obj = new JSONObject(json);
				return rewardRepository.find(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public RewardBean findById(Integer id) {
			Optional<RewardBean> optional = rewardRepository.findById(id);
			if(optional != null&& optional.isPresent()) {
				return optional.get();
			}
			return null;
			
		}
		public boolean exists(Integer id) {
			return rewardRepository.existsById(id);
		}
	

		public RewardBean modify(String json) {
			try {
				JSONObject obj = new JSONObject(json);
				Integer id = obj.isNull("id") ? null : obj.getInt("id") ;
				String memberId = obj.isNull("memberId") ? null : obj.getString("memberId");
				Integer orderNumber = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber");
				String receivedDate = obj.isNull("receivedDate") ? null : obj.getString("receivedDate");
				Integer receivedPoints = obj.isNull("receivedPoints") ? null : obj.getInt("receivedPoints");
				String usedDate = obj.isNull("usedDate") ? null : obj.getString("usedDate");
				Integer usedPoints = obj.isNull("usedPoints") ? null : obj.getInt("usedPoints");
				Integer currentPoints = obj.isNull("currentPoints") ? null : obj.getInt("currentPoints");

				Optional<RewardBean> optional = rewardRepository.findById(id);
				if(optional != null && optional.isPresent()) {
					
					RewardBean update = optional.get();
					update.setId(id);
					update.setMemberId(memberId);
					update.setOrderNumber(orderNumber);
					update.setReceivedDate(DatetimeConverter.parse(receivedDate, "yyyy-MM-dd"));
					update.setReceivedPoints(receivedPoints);
					update.setUsedDate(DatetimeConverter.parse(usedDate, "yyyy-MM-dd"));
					update.setUsedPoints(usedPoints);
					update.setCurrentPoints(currentPoints);
					return rewardRepository.save(update);
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		public boolean remove(Integer id) {
			try {
				if(rewardRepository.existsById(id)) {
					rewardRepository.deleteById(id);
					return true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			return false;
		}
		
		
	
	

}
