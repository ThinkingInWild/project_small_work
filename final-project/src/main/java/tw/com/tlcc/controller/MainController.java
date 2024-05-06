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

import tw.com.tlcc.domain.MainBean;
import tw.com.tlcc.service.MainRepositoryService;

@RestController
@RequestMapping(path = "/pages")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
public class MainController {

	@Autowired
	private MainRepositoryService mainRepositoryService;
	
	@GetMapping(path = "/main/exists/{main_id}")
	public String exist(@PathVariable(name = "main_id") String mainId,@RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		if (mainRepositoryService.exists(mainId)) {
			responseJson.put("message", "資料存在");
			responseJson.put("success", false);
		}else {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", true);
		}
		return responseJson.toString();

	}
	
	@PutMapping(path = "/main/{main_id}")
	public String modify(@PathVariable("main_id") String mainId, @RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		
		if (!mainRepositoryService.exists(mainId)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		}else {
			MainBean main = null;
			
			try {
				main = mainRepositoryService.modify(body);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (main == null) {
				responseJson.put("message", "修改失敗");
				responseJson.put("success", false);
			}else {
				responseJson.put("message", "修改成功");
				responseJson.put("success", true);
				
			}
		}
		return responseJson.toString();
		
	}
	
	@DeleteMapping(path = "/main/{main_id}")
	public String remove(@PathVariable("main_id") String mainId) {
		JSONObject responseJson = new JSONObject();
		
		if (!mainRepositoryService.exists(mainId)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		}else {
			if(mainRepositoryService.remove(mainId)) {
				responseJson.put("message", "刪除成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "刪除失敗");
				responseJson.put("success", false);
			}
		
		}
		return responseJson.toString();
	}
	
	@PostMapping(path = "/main")
	public String create(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject obj = new JSONObject(body);
		String mainId = obj.isNull("mainId") ? null : obj.getString("mainId");

		if (mainRepositoryService.exists(mainId)) {
			responseJson.put("message", "資料已存在");
			responseJson.put("success", false);
		}else {
			MainBean main = null;
			
			try {
				main = mainRepositoryService.create(body);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(main==null) {
				responseJson.put("message", "新增失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("message", "新增成功");
				responseJson.put("success", true);
			}

		}
		return responseJson.toString();
	}
	
	@GetMapping(path = "/main/{main_id}")
	public String findById(@PathVariable(name = "main_id") String mainId) {
		JSONObject responseJson = new JSONObject();
		
		MainBean main = mainRepositoryService.findById(mainId);
		
		if(main!=null) {
		JSONObject obj = new JSONObject()
				.put("mainId", main.getMainId())
				.put("mainName", main.getMainName())
				.put("mainPrice", main.getMainPrice())
				.put("extraMainPrice", main.getExtraMainPrice());
		
		responseJson.put("list",obj);
		}
		return responseJson.toString();
	}
	
	@PostMapping(path = "/main/find")
	public String find(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		
		long count = mainRepositoryService.count(body);
		responseJson.put("count", count);
		
		JSONArray array = new JSONArray();
		List<MainBean> result = mainRepositoryService.find(body);
		if(result!=null && !result.isEmpty()) {
			for(MainBean main : result) {
				JSONObject obj = new JSONObject()
						.put("mainId", main.getMainId())
						.put("mainName", main.getMainName())
						.put("mainPrice", main.getMainPrice())
						.put("extraMainPrice", main.getExtraMainPrice());
				
				array = array.put(obj);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
}
