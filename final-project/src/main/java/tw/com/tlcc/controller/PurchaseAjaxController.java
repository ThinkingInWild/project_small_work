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
import tw.com.tlcc.domain.PurchaseBean;
import tw.com.tlcc.service.PurchaseRepositoryService;
import tw.com.tlcc.util.DatetimeConverter;

@RestController
@RequestMapping(path = "/pages/ajax")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
public class PurchaseAjaxController {
	
	@Autowired
	private PurchaseRepositoryService purchaseRepositoryService;
	
	@GetMapping(path="/purchase/exists/{id}")
	public String exist(@PathVariable(name = "id") Integer id) {
		JSONObject responseJson = new JSONObject();
		if(purchaseRepositoryService.exists(id)) {
			responseJson.put("message", "資料存在");
			responseJson.put("success", false);
		} else {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", true);
		}
		return responseJson.toString();
	}

	
	@PostMapping(path = "/purchase")
	public String create(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();

		JSONObject obj = new JSONObject(body);
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		
		if(purchaseRepositoryService.exists(id)) {
			responseJson.put("message", "資料已存在");
			responseJson.put("success", false);
		} else {
			PurchaseBean order = null;
			try {
				order = purchaseRepositoryService.create(body);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(order==null) {
				responseJson.put("message", "新增失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("message", "新增成功");
				responseJson.put("success", true);
			}
		}
		return responseJson.toString();
	}
	
	@PutMapping(path = "/purchase/{id}")
	public String modify(@PathVariable("id") Integer id, @RequestBody String body) {
		JSONObject responseJson = new JSONObject();

		if(!purchaseRepositoryService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			PurchaseBean product = null;
			try {
				product = purchaseRepositoryService.modify(body);
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
	
	@DeleteMapping(path = "/purchase/{id}")
	public String remove(@PathVariable("id") Integer id) {
		JSONObject responseJson = new JSONObject();
		
		if(!purchaseRepositoryService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			if(purchaseRepositoryService.remove(id)) {
				responseJson.put("message", "刪除成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "刪除失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}
	
	
	
	@GetMapping(path = "/purchase/{id}")
	public String findById(@PathVariable(name = "id") Integer id) {
		JSONObject responseJson = new JSONObject();

		JSONArray array = new JSONArray();
		PurchaseBean order = purchaseRepositoryService.findById(id);
		if(order!=null) {
			String orderDate = DatetimeConverter.toString(order.getOrderDate(), "yyyy-MM-dd");
			String pickupDate = DatetimeConverter.toString(order.getPickupDate(), "yyyy-MM-dd");
			JSONObject item = new JSONObject()
					.put("id", order.getId())
					.put("orderNumber", order.getOrderNumber())
					.put("memberId", order.getMemberId())
					.put("orderAddress", order.getMemberId())
					.put("orderDate", orderDate)
					.put("pickupDate", pickupDate)
					.put("orderTotal", order.getOrderTotal())
					.put("chicken", order.getChicken())
					.put("pork", order.getPork())
					.put("beef", order.getBeef())
					.put("lamb", order.getLamb())
					.put("fish", order.getFish())
					.put("extraChicken", order.getExtraChicken())
					.put("extraPork", order.getExtraPork())
					.put("extraBeef", order.getExtraBeef())
					.put("extraLamb", order.getExtraLamb())
					.put("extraFish", order.getExtraFish());
		
			array = array.put(item);
		}
		responseJson.put("list", array);
		
		return responseJson.toString();
	}
	
	
	@PostMapping(path = "/purchase/find")
	public String find(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		
		long count = purchaseRepositoryService.count(body);
		responseJson.put("count", count);
		
		JSONArray array = new JSONArray();
		List<PurchaseBean> result = purchaseRepositoryService.find(body);
		if(result!=null && !result.isEmpty()) {
			for(PurchaseBean order : result) {
				String orderDate = DatetimeConverter.toString(
						order.getOrderDate(), "yyyy-MM-dd");
				String pickupDate = DatetimeConverter.toString(
						order.getPickupDate(), "yyyy-MM-dd");
				JSONObject item = new JSONObject()
						.put("id", order.getId())
						.put("orderNumber", order.getOrderNumber())
						.put("memberId", order.getMemberId())
						.put("orderAddress", order.getOrderAddress())
						.put("orderDate", orderDate)
						.put("pickupDate", pickupDate)
						.put("orderTotal", order.getOrderTotal())
						.put("chicken", order.getChicken())
						.put("pork", order.getPork())
						.put("beef", order.getBeef())
						.put("lamb", order.getLamb())
						.put("fish", order.getFish())
						.put("extraChicken", order.getExtraChicken())
						.put("extraPork", order.getExtraPork())
						.put("extraBeef", order.getExtraBeef())
						.put("extraLamb", order.getExtraLamb())
						.put("extraFish", order.getExtraFish());
				
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}	
	
	
}
