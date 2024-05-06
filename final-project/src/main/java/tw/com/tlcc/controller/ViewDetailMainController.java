package tw.com.tlcc.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.tlcc.domain.ViewDetailMainBean;
import tw.com.tlcc.service.ViewDetailMainService;

@RestController
@RequestMapping(path = "/pages/ajax")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
public class ViewDetailMainController {

	@Autowired
	private ViewDetailMainService viewDetailMainService;
	
	@GetMapping(path="/detailAndMain/exists/{id}")
	public String exist(@PathVariable(name = "id") Integer id) {
		JSONObject responseJson = new JSONObject();
		if(viewDetailMainService.exists(id)) {
			responseJson.put("message", "資料存在");
			responseJson.put("success", false);
		} else {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", true);
		}
		return responseJson.toString();
	}
	
	@GetMapping(path = "/detailAndMain/{id}")
	public String findById(@PathVariable(name = "id") Integer id) {
		JSONObject responseJson = new JSONObject();

		JSONArray array = new JSONArray();
		ViewDetailMainBean detailAndMain = viewDetailMainService.findById(id);
		if(detailAndMain!=null) {
			JSONObject item = new JSONObject()
					.put("id", detailAndMain.getId())
					.put("orderNumber", detailAndMain.getOrderNumber())
					.put("main", detailAndMain.getMain())
					.put("side1", detailAndMain.getSide1())
					.put("side2", detailAndMain.getSide2())
					.put("side3", detailAndMain.getSide3())
					.put("side4", detailAndMain.getSide4())
					.put("extrMain", detailAndMain.getExtraMain())
					.put("detailTotal", detailAndMain.getDetailTotal())
					.put("mainId", detailAndMain.getMainId())
					.put("mainName", detailAndMain.getMainName())
					.put("mainPrice", detailAndMain.getMainPrice())
					.put("extraMainPrice", detailAndMain.getExtraMainPrice());
		
			array = array.put(item);
		}
		responseJson.put("list", array);
		
		return responseJson.toString();
	}
	
	@PostMapping(path = "/detailAndMain/find")
	public String find(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		
		long count = viewDetailMainService.count(body);
		responseJson.put("count", count);
		
		JSONArray array = new JSONArray();
		List<ViewDetailMainBean> result = viewDetailMainService.find(body);
		if(result!=null && !result.isEmpty()) {
			for(ViewDetailMainBean detailAndMain : result) {
				JSONObject item = new JSONObject()
						.put("id", detailAndMain.getId())
						.put("orderNumber", detailAndMain.getOrderNumber())
						.put("main", detailAndMain.getMain())
						.put("side1", detailAndMain.getSide1())
						.put("side2", detailAndMain.getSide2())
						.put("side3", detailAndMain.getSide3())
						.put("side4", detailAndMain.getSide4())
						.put("extraMain", detailAndMain.getExtraMain())
						.put("detailTotal", detailAndMain.getDetailTotal())
						.put("mainId", detailAndMain.getMainId())
						.put("mainName", detailAndMain.getMainName())
						.put("mainPrice", detailAndMain.getMainPrice())
						.put("extraMainPrice", detailAndMain.getExtraMainPrice());
				
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}	
	
}
