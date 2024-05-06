package tw.com.tlcc.service;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tlcc.domain.RewardBean;

//@Transactional
@SpringBootTest
public class RewardRepositoryServiceTest {

	@Autowired
	private RewardRepositoryService rewardRepositoryService;
	
	
//	@Test
//	public void testCreate() {
//		JSONObject obj = new JSONObject()
//				.put("memberId", "0922390722")
//				.put("orderNumber", 222)
//				.put("receivedDate", "2023-9-28")
//				.put("receivedPoints", 100)
//				.put("usedDate",  "2023-9-28")
//				.put("usedPoints", 20)
//				.put("currentPoints", 100);
//		
//				
//		RewardBean create = rewardRepositoryService.create(obj.toString());
//		System.out.println("create="+ create);
//	}
		
//	@Test
//	public void testModify() {
//		JSONObject obj = new JSONObject()
//				.put("memberId","0922390722")
//				.put("orderNumber", 111)
//				.put("receivedDate", "2023-9-28")
//				.put("receivedPoints", 100)
//				.put("usedDate",  "2023-9-28")
//				.put("usedPoints", 20)
//				.put("currentPoints", 100);
//		
//		RewardBean modify = rewardRepositoryService.modify(obj.toString());
//		System.out.println("modify="+modify);
//	}
	
//	@Test
//	public void testRemove() {
//		boolean remove = rewardRepositoryService.remove(1002);
//		System.out.println("remove="+remove);
//	}
//
//
//	@Test
//	public void testCount() {
//		JSONObject obj1 = new JSONObject()
//				.put("name", "a");
//		long count1 = memberRepositoryService.count(obj1.toString());
//		System.out.println("count1="+count1);
//
//		JSONObject obj2 = new JSONObject()
//				.put("id", "7");
//		long count2 = memberRepositoryService.count(obj2.toString());
//		System.out.println("count2="+count2);
//	}
//	
//	@Test
//	public void testFind() {
//		JSONObject obj1 = new JSONObject()
//				.put("name", "a");
//		List<MemberBean> find1 = memberRepositoryService.find(obj1.toString());
//		System.out.println("find1="+find1);
//
//		JSONObject obj2 = new JSONObject()
//				.put("id", "7");
//		List<MemberBean> find2 = memberRepositoryService.find(obj2.toString());
//		System.out.println("find2="+find2);
//	}
//	
//	@Test
//	public void testFindById() {
//		MemberBean findById = memberRepositoryService.findById(5);
//		System.out.println("findById="+findById);
//	}
//	
//	@Test
//	public void testExists() {
//		boolean exists = memberRepositoryService.exists(6);
//		System.out.println("exists="+exists);
//	}

}



