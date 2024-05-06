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

import tw.com.tlcc.domain.BillboardBean;
import tw.com.tlcc.service.BillboardRepositoryService;
import tw.com.tlcc.util.DatetimeConverter;


@RestController
@RequestMapping(path= {"/pages/ajax"})
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
public class BillboardController {
	@Autowired
	private BillboardRepositoryService  billboardRepositoryService;
	
	@GetMapping("/billboard/{id}")
	public String findById(@PathVariable(name="id") Integer id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		BillboardBean bean = billboardRepositoryService.findById(id);
		if(bean!=null) {
			String messageTime = DatetimeConverter.toString(bean.getMessageTime(), "yyyy-MM-dd HH:mm");
			JSONObject item = new JSONObject()
					.put("id", bean.getId())
					.put("alias", bean.getAlias())
					.put("content", bean.getContent())
					.put("messageTime", messageTime)
					.put("pushNumber", bean.getPushNumber())
					.put("youSuckNumber", bean.getYouSuckNumber())
					.put("viewCount", bean.getViewCount())
					;
			array = array.put(item);
		}
		responseJson.put("list", array);
		return responseJson.toString(); 
	}
	
	@PostMapping(value="/billboard", produces = "application/json;charset=UTF-8")
	public String create(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		if(billboardRepositoryService.exists(id)) {
			responseJson.put("message", "資料已存在");
			responseJson.put("success", false);
		} else {
			BillboardBean bean = billboardRepositoryService.create(json);
			if (bean != null) {
				responseJson.put("message", "新增成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "新增失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}
	
	@DeleteMapping(value="/billboard/{id}")
	public String remove(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		if(!billboardRepositoryService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			if (billboardRepositoryService.remove(id)) {
				responseJson.put("message", "刪除成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "刪除失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}
	
	@PutMapping(value="/billboard/{id}")
	public String modify(@PathVariable Integer id, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		if(!billboardRepositoryService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			BillboardBean bean = billboardRepositoryService.modify(json);
			if(bean != null) {
				responseJson.put("message", "修改成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "修改失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}
	
	@PostMapping(value="/billboard/find")
	public String find(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		long count = billboardRepositoryService.count(json);
		responseJson.put("count", count);
		
		List<BillboardBean> beans = billboardRepositoryService.find(json);
		JSONArray array = new JSONArray();
		if(beans!=null && !beans.isEmpty()) {
			for(BillboardBean bean : beans) {
				String messageTime = DatetimeConverter.toString(bean.getMessageTime(), "yyyy-MM-dd HH:mm");
				JSONObject item = new JSONObject()
						.put("id", bean.getId())
						.put("alias", bean.getAlias())
						.put("content", bean.getContent())
						.put("messageTime", messageTime)
						.put("pushNumber", bean.getPushNumber())
						.put("youSuckNumber", bean.getYouSuckNumber())
						.put("viewCount", bean.getViewCount())
						;
				array = array.put(item);
			}
		} 
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	
}






