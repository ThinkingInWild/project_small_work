package tw.com.tlcc.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.tlcc.domain.InventoryBean;
import tw.com.tlcc.service.InventoryRepositoryService;
import tw.com.tlcc.util.DatetimeConverter;

@RestController
@RequestMapping(path = "/pages/ajax")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
public class InventoryController {
	
	@Autowired
	private InventoryRepositoryService inventoryRepositoryService;
	
	@PutMapping(path = "/inventory")
	public String modify(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();

		
			InventoryBean bean1 = null;
			InventoryBean bean2 = null;
			InventoryBean bean3 = null;
			InventoryBean bean4 = null;
			InventoryBean bean5 = null;
			try {
				bean1 = inventoryRepositoryService.updatePork(body);
				bean2 = inventoryRepositoryService.updateBeef(body);
				bean3 = inventoryRepositoryService.updateLamb(body);
				bean4 = inventoryRepositoryService.updateFish(body);
				bean5 = inventoryRepositoryService.updateChicken(body);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			if(bean1 == null && bean2 == null && bean3 == null && bean4 == null && bean5 == null) {
				responseJson.put("message", "修改失敗");
				responseJson.put("success", false);
			}
			else {
				responseJson.put("message", "修改成功");
				responseJson.put("success", true);
			}
		
		return responseJson.toString();
	}
	
	@PostMapping(path = "/inventory/find")
	public String find(@RequestBody String body) {
		
		JSONObject responseJson = new JSONObject();
		
		long count = inventoryRepositoryService.count(body);
		responseJson.put("count", count);
		
		JSONArray array = new JSONArray();
		List<InventoryBean> result = inventoryRepositoryService.find(body);
		if(result!=null && !result.isEmpty()) {
			for(InventoryBean order : result) {
				
				String inventoryDate = DatetimeConverter.toString(
						order.getInventoryDate(), "yyyy-MM-dd");
				
				JSONObject item = new JSONObject()
						.put("id", order.getId())
						.put("inventoryDate", inventoryDate)
						.put("pork", order.getPork())
						.put("beef", order.getBeef())
						.put("lamb", order.getLamb())
						.put("fish", order.getFish())
						.put("chicken", order.getChicken());
						
				
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}	
	
	
	
}
