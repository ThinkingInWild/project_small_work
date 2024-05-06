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

import tw.com.tlcc.domain.MemberBean;
import tw.com.tlcc.service.MemberRepositoryService;
import tw.com.tlcc.util.DatetimeConverter;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
@RestController
@RequestMapping(path= {"/pages/ajax"})
public class MemberController {
	@Autowired
	private MemberRepositoryService  memberRepositoryService;
	
	@GetMapping("/members/{id}")
	public String findById(@PathVariable(name="id") Integer id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		MemberBean member = memberRepositoryService.findById(id);
		if(member!=null) {
			String signUpDate = DatetimeConverter.toString(member.getSignUpDate(), "yyyy-MM-dd");
			String finalLogInDate = DatetimeConverter.toString(member.getFinalLogInDate(), "yyyy-MM-dd");
			JSONObject item = new JSONObject()
					.put("id", member.getId())
					.put("memberId", member.getMemberId())
					.put("memberName", member.getMemberName())
					.put("email", member.getEmail())
					.put("gender", member.getGender())
					.put("memberAddress", member.getMemberAddress())
					.put("discountPointsLeft", member.getDiscountPointsLeft())
					.put("signUpDate", signUpDate)
					.put("finalLogInUpDate", finalLogInDate)
					.put("accessLevel", member.getAccessLevel())
					.put("couponQuantity", member.getCouponQuantity())
					;
			array = array.put(item);
		}
		responseJson.put("list", array);
		return responseJson.toString(); 
	}
	
	@PostMapping(value="/members", produces = "application/json;charset=UTF-8")
	public String create(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		if(memberRepositoryService.exists(id)) {
			responseJson.put("message", "資料已存在");
			responseJson.put("success", false);
		} else {
			MemberBean member = memberRepositoryService.create(json);
			if (member != null) {
				responseJson.put("message", "新增成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "新增失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}
	
	@DeleteMapping(value="/members/{id}")
	public String remove(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		if(!memberRepositoryService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			if (memberRepositoryService.remove(id)) {
				responseJson.put("message", "刪除成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "刪除失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}
	
	@PutMapping(value="/members/{id}")
	public String modify(@PathVariable Integer id, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		if(!memberRepositoryService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			MemberBean member = memberRepositoryService.modify(json);
			if(member != null) {
				responseJson.put("message", "修改成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "修改失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}
	
	@PostMapping(value="/members/find")
	public String find(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		System.out.print("\n");
		System.out.println("接收JSON字串");
		System.out.println(json);
		System.out.print("\n");
		
		long count = memberRepositoryService.count(json);
		responseJson.put("count", count);
		
		List<MemberBean> members = memberRepositoryService.find(json);
		JSONArray array = new JSONArray();
		if(members!=null && !members.isEmpty()) {
			for(MemberBean member : members) {
				String signUpDate = DatetimeConverter.toString(member.getSignUpDate(), "yyyy-MM-dd");
				String finalLogInDate = DatetimeConverter.toString(member.getFinalLogInDate(), "yyyy-MM-dd");
				JSONObject item = new JSONObject()
						.put("id", member.getId())
						.put("memberId", member.getMemberId())
						.put("memberName", member.getMemberName())
						.put("email", member.getEmail())
						.put("gender", member.getGender())
						.put("memberAddress", member.getMemberAddress())
						.put("discountPointsLeft", member.getDiscountPointsLeft())
						.put("signUpDate", signUpDate)
						.put("finalLogInUpDate", finalLogInDate)
						.put("accessLevel", member.getAccessLevel())
						.put("couponQuantity", member.getCouponQuantity())
						;
				array = array.put(item);
			}
		} 
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	
}






