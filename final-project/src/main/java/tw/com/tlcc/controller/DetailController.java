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

import tw.com.tlcc.domain.DetailBean;
import tw.com.tlcc.service.DetailRepositoryService;
 
@RestController
@RequestMapping(path = "/pages/ajax")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
public class DetailController {

	@Autowired
	private DetailRepositoryService detailRepositoryService;
	
	@GetMapping(path = "/detail/exists/{id}")
	public String exist(@PathVariable(name = "id") Integer id ,@RequestBody String body) {
		
			JSONObject responseJson = new JSONObject();
			if (detailRepositoryService.exists(id)) {
				responseJson.put("message", "資料存在");
				responseJson.put("success", false);
			}else {
				responseJson.put("message", "資料不存在");
				responseJson.put("success", true);
			}
			return responseJson.toString();

	}
	
	@PutMapping(path = "/detail/{id}")
	public String modify(@PathVariable("id") Integer id, @RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		
		if (!detailRepositoryService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		}else {
			DetailBean detail = null;
			
			try {
				detail = detailRepositoryService.modify(body);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (detail == null) {
				responseJson.put("message", "修改失敗");
				responseJson.put("success", false);
			}else {
				responseJson.put("message", "修改成功");
				responseJson.put("success", true);
				
			}
		}
		return responseJson.toString();
		
	}
	
	@DeleteMapping(path = "/detail/{id}")
	public String remove(@PathVariable("id") Integer id) {
		JSONObject responseJson = new JSONObject();
		
		if (!detailRepositoryService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		}else {
			if(detailRepositoryService.remove(id)) {
				responseJson.put("message", "刪除成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "刪除失敗");
				responseJson.put("success", false);
			}
		
		}
		return responseJson.toString();
	}

	@PostMapping(path = "/detail")
	public String create(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject obj = new JSONObject(body);
		Integer id = obj.isNull("id") ? null : obj.getInt("id");

		if (detailRepositoryService.exists(id)) {
			responseJson.put("message", "資料已存在");
			responseJson.put("success", false);
		}else {
			DetailBean detail = null;
			
			try {
				detail = detailRepositoryService.create(body);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(detail==null) {
				responseJson.put("message", "新增失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("message", "新增成功");
				responseJson.put("success", true);
			}

		}
		return responseJson.toString();
	}
	
	@GetMapping(path = "/detail/{id}")
	public String findById(@PathVariable(name = "id") Integer id) {
		JSONObject responseJson = new JSONObject();
		
		DetailBean detail = detailRepositoryService.findById(id);
		
		if(detail!=null) {
			JSONObject obj = new JSONObject()
					.put("id", detail.getId())
					.put("orderNumber", detail.getOrderNumber())
					.put("main", detail.getMain())
					.put("side1", detail.getSide1())
					.put("side2", detail.getSide2())
					.put("side3", detail.getSide3())
					.put("side4", detail.getSide4())
					.put("extraMain", detail.getExtraMain())
					.put("detailTotal", detail.getDetailTotal());
			
			responseJson.put("list",obj);

		}
		return responseJson.toString();

	}
	
	@PostMapping(path = "/detail/find")
	public String find(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();

		long count = detailRepositoryService.count(body);
		responseJson.put("count", count);
		
		JSONArray array = new JSONArray();
		List<DetailBean> result = detailRepositoryService.find(body);
		if(result!=null && !result.isEmpty()) {
			for(DetailBean detail : result) {
				JSONObject obj = new JSONObject()
						.put("id", detail.getId())
						.put("orderNumber", detail.getOrderNumber())
						.put("main", detail.getMain())
						.put("side1", detail.getSide1())
						.put("side2", detail.getSide2())
						.put("side3", detail.getSide3())
						.put("side4", detail.getSide4())
						.put("extraMain", detail.getExtraMain())
						.put("detailTotal", detail.getDetailTotal());

				array = array.put(obj);

			}
		}

		responseJson.put("list", array);
		return responseJson.toString();

	}
	
	
	@PostMapping(path = "/multipleDetails")
	public String createMultipleData(@RequestBody String body) {
		System.out.println(body);
		JSONArray jArray = new JSONArray(body);
		System.out.println("jArray: " + jArray);
		JSONObject responseJson = new JSONObject();
		JSONObject obj = new JSONObject();
		
		
		for(int i = 0; i <= (jArray.length() - 1); i++) {
		obj = jArray.getJSONObject(i);
		
		Integer id = obj.isNull("id") ? null : obj.getInt("id");

		String objString = obj.toString();

		if (detailRepositoryService.exists(id)) {
			responseJson.put("message", "資料已存在");
			responseJson.put("success", false);
		}else {
			DetailBean detail = null;
			
			try {
				detail = detailRepositoryService.create(objString);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(detail==null) {
				responseJson.put("message", "新增失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("message", "新增成功");
				responseJson.put("success", true);
			}
		}	
		}
		return responseJson.toString();
		
	}
	
	
}
