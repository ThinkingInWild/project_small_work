package tw.com.tlcc.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.tlcc.domain.RewardBean;
import tw.com.tlcc.service.RewardRepositoryService;
import tw.com.tlcc.util.DatetimeConverter;

@RestController
@RequestMapping(path = "/pages/ajax")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
public class RewardController {
	@Autowired
	private RewardRepositoryService rewardRepositoryService;         //將DAO改用Repository且改用Repository Service，這裡改ProductRepositoryService
//	private String memberId;
	
	@GetMapping(path="/products/exists/{id}")
	public String existsByMemberId(@PathVariable(name = "id") Integer id) {
		JSONObject responseJson = new JSONObject();
		if(rewardRepositoryService.exists(id)) {
			responseJson.put("message", "資料存在");
			responseJson.put("success", false);
		} else {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", true);
		}
		return responseJson.toString();
	}
	
	@PutMapping(path = "/products/{memberId}")
	public String modify(@PathVariable("memberId") Integer id, @RequestBody String body) {
		JSONObject responseJson = new JSONObject();

		if(!rewardRepositoryService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			RewardBean product = null;
			try {
				product = rewardRepositoryService.modify(body);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(product==null) {
				responseJson.put("message", "修改失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("message", "修改成功");
				responseJson.put("success", true);
			}
		}
		return responseJson.toString();
	}
	
	@DeleteMapping(path = "/products/{memberId}")
	public String remove(@PathVariable("memberId") Integer id) {
		JSONObject responseJson = new JSONObject();
		
		if(!rewardRepositoryService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			if(rewardRepositoryService.remove(id)) {
				responseJson.put("message", "刪除成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "刪除失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}
	 
	@PostMapping(path = "/products")
	public String create(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();

		JSONObject obj = new JSONObject(body);
		Integer id = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber");
		
		if(rewardRepositoryService.exists(id)) {
			responseJson.put("message", "資料已存在");
			responseJson.put("success", false);
		} else {
			RewardBean reward = null;
			try {
				reward = rewardRepositoryService.create(body);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(reward==null) {
				responseJson.put("message", "新增失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("message", "新增成功");
				responseJson.put("success", true);
			}
		}
		return responseJson.toString();
	}
	
	@GetMapping(path = "/products/{memberId}")
	public String findById(@PathVariable(name = "memberId") Integer id) {
		JSONObject responseJson = new JSONObject();

		JSONArray array = new JSONArray();
		RewardBean reward = rewardRepositoryService.findById(id);
		
		if(reward!=null) {
			String receivedDate = DatetimeConverter.toString(reward.getReceivedDate(), "yyyy-MM-dd");
			String usedDate = DatetimeConverter.toString(reward.getUsedDate(), "yyyy-MM-dd");
			JSONObject item = new JSONObject()
					.put("id", reward.getId())
					.put("memberId", reward.getMemberId())
					.put("memberId", reward.getMemberId())
					.put("orderNumber", reward.getOrderNumber())
					.put("receivedDate",receivedDate)
					.put("receivedPoints",reward.getReceivedPoints())
					.put("usedDate", usedDate)
					.put("usedPoints",reward.getUsedPoints())
					.put("currentPoints", reward.getCurrentPoints());
			
			array = array.put(item);
		}
		responseJson.put("list", array);
		
		return responseJson.toString();
	}

	@PostMapping(path = "/products/find")
	public String find(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		
		long count = rewardRepositoryService.count(body);
		responseJson.put("count", count);
		
		JSONArray array = new JSONArray();
		List<RewardBean> result = rewardRepositoryService.find(body);
		if(result!=null && !result.isEmpty()) {
			for(RewardBean reward : result) {
				
				
				String receivedDate = DatetimeConverter.toString(
						reward.getReceivedDate(), "yyyy-MM-dd");
				String usedDate = DatetimeConverter.toString(
						reward.getUsedDate(), "yyyy-MM-dd");

				JSONObject item = new JSONObject()
						.put("id", reward.getId())
						.put("memberId", reward.getMemberId())
						.put("orderNumber", reward.getOrderNumber())
						.put("receivedDate", receivedDate)
						.put("receivedPoints",reward.getReceivedPoints())
						.put("usedDate", usedDate)
						.put("usedPoints",reward.getUsedPoints())
						.put("currentPoints", reward.getCurrentPoints());
				
				System.out.println(usedDate);
				System.out.println(receivedDate);
				
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}	
}
