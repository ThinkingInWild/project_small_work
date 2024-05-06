package tw.com.tlcc.repository;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import tw.com.tlcc.domain.MemberBean;

@SpringBootTest
@Transactional
public class MemberRepositoryDAOImplTests {
	@Autowired
	MemberRepositoryDAOImpl testDAO;
	
	@Test
	public void testFind() {
		
		JSONObject input = new JSONObject();
		
		input.put("id", 1);
		
		List<MemberBean> result = testDAO.find(input);
		
		System.out.println(result);
	}

}
