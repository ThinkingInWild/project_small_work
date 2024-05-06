package tw.com.tlcc.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.tlcc.domain.MainDetailBean;
import tw.com.tlcc.service.MainDetailRepositoryService;

@RestController
@RequestMapping(path = "/pages/ajax")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
public class MainDetailController {

	@Autowired
	private MainDetailRepositoryService mainDetailRepositoryService;
	
	@PostMapping(path = "/mainDetail/find")
	public String find(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		
		long count = mainDetailRepositoryService.count(body);
		responseJson.put("count", count);
		
		JSONArray array = new JSONArray();
		List<MainDetailBean> result = mainDetailRepositoryService.find(body);
		if(result!=null && !result.isEmpty()) {
			for(MainDetailBean main : result) {
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
