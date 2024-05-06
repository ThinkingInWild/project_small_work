package tw.com.tlcc.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tlcc.domain.MemberBean;
import tw.com.tlcc.repository.MemberRepository;
import tw.com.tlcc.util.DatetimeConverter;

@Service
@Transactional
public class MemberRepositoryService {

	@Autowired
	private MemberRepository memberRepository;
	
	//新增
	public MemberBean create(String json) {						
		try{ 
			JSONObject obj = new JSONObject(json);
			Integer	id = obj.isNull("id") ? 0 : obj.getInt("id");
			String	memberId = obj.isNull("memberId") ? null : obj.getString("memberId");
			String	memberPassword = obj.isNull("memberPassword") ? null : obj.getString("memberPassword");
			String memberName = obj.isNull("memberName") ? null : obj.getString("memberName");
			String email = obj.isNull("email") ? null : obj.getString("email") ;		
			Integer gender = obj.isNull("gender") ? null : obj.getInt("gender");
			String memberAddress  = obj.isNull("memberAddress") ? null : obj.getString("memberAddress") ;
			Integer discountPointsLeft = obj.isNull("discountPointsLeft") ? null : obj.getInt("discountPointsLeft") ;
			String signUpDate = obj.isNull("signUpDate") ? null : obj.getString("signUpDate") ;
			String finalLogInDate = obj.isNull("finalLogInDate") ? null : obj.getString("finalLogInDate");
			String accessLevel = obj.isNull("accessLevel") ? null : obj.getString("accessLevel");
			Integer couponQuantity = obj.isNull("couponQuantity") ? null : obj.getInt("couponQuantity");
			
			MemberBean insert = new MemberBean(); 
			insert.setId(id);
			insert.setMemberId(memberId);
			insert.setMemberPassword(memberPassword);
			insert.setMemberName(memberName);
			insert.setEmail(email);
			insert.setGender(gender);
			insert.setMemberAddress(memberAddress);
			insert.setDiscountPointsLeft(discountPointsLeft);
			insert.setSignUpDate(DatetimeConverter.parse(signUpDate, "yyyy-MM-dd"));
			insert.setFinalLogInDate(DatetimeConverter.parse(finalLogInDate, "yyyy-MM-dd"));
			insert.setAccessLevel(accessLevel);
			insert.setCouponQuantity(couponQuantity);
			
			if(!memberRepository.existsById(id)){
				return memberRepository.save(insert);
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//修改
	public MemberBean modify(String json) {											
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id") ;
			String memberId = obj.isNull("memberId") ? null : obj.getString("memberId");
			String memberPassword = obj.isNull("memberPassword") ? null : obj.getString("memberPassword");
			String memberName = obj.isNull("memberName") ? null : obj.getString("memberName");
			String email = obj.isNull("email") ? null : obj.getString("email") ;
			Integer gender = obj.isNull("gender") ? null : obj.getInt("gender");
			String memberAddress  = obj.isNull("memberAddress") ? null : obj.getString("memberAddress") ;
			Integer discountPointsLeft = obj.isNull("discountPointsLeft") ? null : obj.getInt("discountPointsLeft") ;
			String signUpDate = obj.isNull("signUpDate") ? null : obj.getString("signUpDate") ;
			String finalLogInDate = obj.isNull("finalLogInDate") ? null : obj.getString("finalLogInDate");

			Optional<MemberBean> optional = memberRepository.findById(id);
			if(optional != null && optional.isPresent()) {
				
				MemberBean update = optional.get();
				update.setId(id);
				update.setMemberId(memberId);
				update.setMemberPassword(memberPassword);
				update.setMemberName(memberName);
				update.setEmail(email);
				update.setGender(gender);
				update.setMemberAddress(memberAddress);
				update.setDiscountPointsLeft(discountPointsLeft);
				update.setSignUpDate(DatetimeConverter.parse(signUpDate, "yyyy-MM-dd"));
				update.setFinalLogInDate(DatetimeConverter.parse(finalLogInDate, "yyyy-MM-dd"));
				return memberRepository.save(update);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查詢資料是否有這筆資料
	public boolean exists(Integer id) {					
		return memberRepository.existsById(id);
	} 
	
	//刪除
	public boolean remove(Integer id) {					
		try {
			if(memberRepository.existsById(id)) {
				memberRepository.deleteById(id);
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	//查全部 JpaRepository原生
	public List<MemberBean> findAll(){ 					
		return memberRepository.findAll();
	}
	
	//查詢byId
	public MemberBean findById(Integer id) {			
		Optional<MemberBean> optional = memberRepository.findById(id);
		if(optional != null&& optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	//(新)為了登入驗證而增加的方法
	public MemberBean findByMemberIdAndMemberPassword(String memberId, String memberPassword) {
		Optional<MemberBean> optional = memberRepository.findByMemberIdAndMemberPassword(memberId, memberPassword);
		if(optional != null&& optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	//條件式查詢
	public List<MemberBean> find(String json) {			
		try {
			JSONObject obj = new JSONObject(json);
			return memberRepository.find(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//計數
	public long count(String json) {					
		try {
			JSONObject obj = new JSONObject(json);
			return memberRepository.count(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	
	
}
