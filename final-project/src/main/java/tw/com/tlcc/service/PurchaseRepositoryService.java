package tw.com.tlcc.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tlcc.domain.PurchaseBean;
import tw.com.tlcc.repository.PurchaseRepository;
import tw.com.tlcc.util.DatetimeConverter;

@Service
@Transactional
public class PurchaseRepositoryService {

	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	
	public PurchaseBean create(String json) {
		
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			Integer orderNumber = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber");
			String memberId = obj.isNull("memberId") ? null : obj.getString("memberId");
			String orderAddress = obj.isNull("orderAddress") ? null : obj.getString("orderAddress");
			String orderDate = obj.isNull("orderDate") ? null : obj.getString("orderDate");
			String pickupDate = obj.isNull("pickupDate") ? null : obj.getString("pickupDate");
			Integer orderTotal = obj.isNull("orderTotal") ? null : obj.getInt("orderTotal");
			Integer chicken = obj.isNull("chicken") ? null : obj.getInt("chicken");
			Integer pork = obj.isNull("pork") ? null : obj.getInt("pork");
			Integer beef = obj.isNull("beef") ? null : obj.getInt("beef");
			Integer lamb = obj.isNull("lamb") ? null : obj.getInt("lamb");
			Integer fish = obj.isNull("fish") ? null : obj.getInt("fish");
			Integer extraChicken = obj.isNull("extraChicken") ? null : obj.getInt("extraChicken");
			Integer extraPork = obj.isNull("extraPork") ? null : obj.getInt("extraPork");
			Integer extraBeef = obj.isNull("extraBeef") ? null : obj.getInt("extraBeef");
			Integer extraLamb = obj.isNull("extraLamb") ? null : obj.getInt("extraLamb");
			Integer extraFish = obj.isNull("extraFish") ? null : obj.getInt("extraFish");
			
			PurchaseBean insert = new PurchaseBean();
			insert.setId(id);
			insert.setOrderNumber(orderNumber);
			insert.setMemberId(memberId);
			insert.setOrderAddress(orderAddress);
			insert.setOrderDate(DatetimeConverter.parse(orderDate, "yyyy-MM-dd"));
			insert.setPickupDate(DatetimeConverter.parse(pickupDate, "yyyy-MM-dd" ));
			insert.setOrderTotal(orderTotal);
			insert.setChicken(chicken);
			insert.setPork(pork);
			insert.setBeef(beef);
			insert.setLamb(lamb);
			insert.setFish(fish);
			insert.setExtraChicken(extraChicken);
			insert.setExtraPork(extraPork);
			insert.setExtraBeef(extraBeef);
			insert.setExtraLamb(extraLamb);
			insert.setExtraFish(extraFish);

			if(!purchaseRepository.existsById(id)){
				return purchaseRepository.save(insert);
			}
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	
	public long count(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return purchaseRepository.count(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<PurchaseBean> find(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return purchaseRepository.find(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public PurchaseBean findById(Integer id) {
		Optional<PurchaseBean> optional = purchaseRepository.findById(id);
		if(optional != null&& optional.isPresent()) {
			return optional.get();
		}
		return null;
		
	}
	
	public boolean exists(Integer id) {
		return purchaseRepository.existsById(id);
	}
	
	public PurchaseBean modify(String json) {
		
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			Integer orderNumber = obj.isNull("orderNumber") ? null : obj.getInt("orderNumber");
			String memberId = obj.isNull("memberId") ? null : obj.getString("memberId");
			String orderAddress = obj.isNull("orderAddress") ? null : obj.getString("orderAddress");
			String orderDate = obj.isNull("orderDate") ? null : obj.getString("orderDate");
			String pickupDate = obj.isNull("pickupDate") ? null : obj.getString("pickupDate");
			Integer orderTotal = obj.isNull("orderTotal") ? null : obj.getInt("orderTotal");
			Integer chicken = obj.isNull("chicken") ? null : obj.getInt("chicken");
			Integer pork = obj.isNull("pork") ? null : obj.getInt("pork");
			Integer beef = obj.isNull("beef") ? null : obj.getInt("beef");
			Integer lamb = obj.isNull("lamb") ? null : obj.getInt("lamb");
			Integer fish = obj.isNull("fish") ? null : obj.getInt("fish");
			Integer extraChicken = obj.isNull("extraChicken") ? null : obj.getInt("extraChicken");
			Integer extraPork = obj.isNull("extraPork") ? null : obj.getInt("extraPork");
			Integer extraBeef = obj.isNull("extraBeef") ? null : obj.getInt("extraBeef");
			Integer extraLamb = obj.isNull("extraLamb") ? null : obj.getInt("extraLamb");
			Integer extraFish = obj.isNull("extraFish") ? null : obj.getInt("extraFish");
			
			Optional<PurchaseBean> optional = purchaseRepository.findById(id);
			if(optional != null && optional.isPresent()) {
				
				PurchaseBean update = optional.get();
				update.setId(id);
				update.setOrderNumber(orderNumber);
				update.setMemberId(memberId);
				update.setOrderAddress(orderAddress);
				update.setOrderDate(DatetimeConverter.parse(orderDate, "yyyy-MM-dd"));
				update.setPickupDate(DatetimeConverter.parse(pickupDate, "yyyy-MM-dd" ));
				update.setOrderTotal(orderTotal);
				update.setChicken(chicken);
				update.setPork(pork);
				update.setBeef(beef);
				update.setLamb(lamb);
				update.setFish(fish);
				update.setExtraChicken(extraChicken);
				update.setExtraPork(extraPork);
				update.setExtraBeef(extraBeef);
				update.setExtraLamb(extraLamb);
				update.setExtraFish(extraFish);


				
				return purchaseRepository.save(update);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean remove(Integer id) {
		try {
			if(purchaseRepository.existsById(id)) {
				purchaseRepository.deleteById(id);
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}
	
}
