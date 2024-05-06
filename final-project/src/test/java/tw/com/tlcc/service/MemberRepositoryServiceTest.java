package tw.com.tlcc.service;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tlcc.domain.MemberBean;

@Transactional
@SpringBootTest
public class MemberRepositoryServiceTest {
//	@Test
//	public void contextLoads() {
//		
//	}
	
	@Autowired
	private MemberRepositoryService memberRepositoryService;
	

	@Test
	public void testCreate() {
		JSONObject obj = new JSONObject()
				.put("id", 101)
				.put("memberId","0915000100")
				.put("memberPassword", "fun")
				.put("memberName", "false")
				.put("email", "abcd@efg")
				.put("gender", 1)
				.put("memberAddress", "新北市")
				.put("discountPointsLeft", 100)
				.put("signUpDate", "2023-01-01")
				.put("finalLogInDate", "2023-01-01")
				.put("accessLevel","54321" )
				.put("couponQuantity", 100);
		

		
				
		MemberBean create = memberRepositoryService.create(obj.toString());
		System.out.println("create="+ create);
	}
		
//	@Test
//	public void testModify() {
//		JSONObject obj = new JSONObject()
//				.put("id", 3)
//				.put("memberId","0915000100")
//				.put("memberPassword", "fun")
//				.put("memberName", "false")
//				.put("email", "abcd@efg")
//				.put("gender", 1)
//				.put("memberAddress", "新北414")
//				.put("discountPointsLeft", 100)
//				.put("signUpDate", "2023-01-01")
//				.put("finalLogInDate", "2023-01-01")
//				.put("accessLevel","54321" )
//				.put("couponQuantity", "100");
//		MemberBean modify = memberRepositoryService.modify(obj.toString());
//		System.out.println("modify="+modify);
//	}
	
//	@Test
//	public void testRemove() {
//		boolean remove = memberRepositoryService.remove(1002);
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
//	
				
}			
		
		
				
				
				
				
				
	
	
	

