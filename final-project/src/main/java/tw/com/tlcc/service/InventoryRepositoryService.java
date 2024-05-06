package tw.com.tlcc.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tlcc.domain.InventoryBean;
import tw.com.tlcc.domain.MemberBean;
import tw.com.tlcc.repository.InventoryRepository;

@Service
@Transactional(rollbackFor = { Exception.class })
public class InventoryRepositoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	public InventoryBean updatePork(String json) {

//		Integer abc = testTableDAO.hqlCount(20230903);
//		System.out.println(abc + "hahaha");

		JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String inventoryDate = obj.isNull("inventoryDate") ? null : obj.getString("inventoryDate");
		Integer pork = obj.isNull("pork") ? null : obj.getInt("pork");
		Integer beef = obj.isNull("beef") ? null : obj.getInt("beef");
		Integer lamb = obj.isNull("lamb") ? null : obj.getInt("lamb");
		Integer fish = obj.isNull("fish") ? null : obj.getInt("fish");
		Integer chicken = obj.isNull("chicken") ? null : obj.getInt("chicken");

		if (pork != null && pork >= 1) {

			int i = 0;
			int j = 0;
			int toBeChanged;

			while (inventoryRepository.find(obj).get(i) != null) {

				if (inventoryRepository.find(obj).get(i).getPork() >= pork) {
					toBeChanged = inventoryRepository.find(obj).get(i).getPork() - pork;
					pork = 0;

				} else {
					toBeChanged = inventoryRepository.find(obj).get(i).getPork() - inventoryRepository.find(obj).get(i).getPork();
					pork = pork - inventoryRepository.find(obj).get(i).getPork();

				}

				InventoryBean testBean = new InventoryBean();
				testBean.setId(inventoryRepository.find(obj).get(i).getId());
				testBean.setInventoryDate(inventoryRepository.find(obj).get(i).getInventoryDate());
				testBean.setPork(toBeChanged);
				testBean.setBeef(inventoryRepository.find(obj).get(i).getBeef());
				testBean.setLamb(inventoryRepository.find(obj).get(i).getLamb());
				testBean.setFish(inventoryRepository.find(obj).get(i).getFish());
				testBean.setChicken(inventoryRepository.find(obj).get(i).getChicken());

				inventoryRepository.update(testBean);

				System.out.println(testBean.toString());

				System.out.println(pork);

				j = i;
				System.out.println(j);

				i = i + 1;
				System.out.println(i);

				if (pork == 0) {
					break;
				}

			}

			return inventoryRepository.find(obj).get(j);

		} else {
			return null;
		}
	}

	public InventoryBean updateBeef(String json) {

		JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String inventoryDate = obj.isNull("inventoryDate") ? null : obj.getString("inventoryDate");
		Integer pork = obj.isNull("pork") ? null : obj.getInt("pork");
		Integer beef = obj.isNull("beef") ? null : obj.getInt("beef");
		Integer lamb = obj.isNull("lamb") ? null : obj.getInt("lamb");
		Integer fish = obj.isNull("fish") ? null : obj.getInt("fish");
		Integer chicken = obj.isNull("chicken") ? null : obj.getInt("chicken");

		if (beef != null && beef >= 1) {

			int i = 0;
			int j = 0;
			int toBeChanged;

			while (inventoryRepository.find(obj).get(i) != null) {

				if (inventoryRepository.find(obj).get(i).getBeef() >= beef) {
					toBeChanged = inventoryRepository.find(obj).get(i).getBeef() - beef;
					beef = 0;

				} else {
					toBeChanged = inventoryRepository.find(obj).get(i).getBeef() - inventoryRepository.find(obj).get(i).getBeef();
					beef = beef - inventoryRepository.find(obj).get(i).getBeef();

				}

				InventoryBean testBean = new InventoryBean();
				testBean.setId(inventoryRepository.find(obj).get(i).getId());
				testBean.setInventoryDate(inventoryRepository.find(obj).get(i).getInventoryDate());
				testBean.setPork(inventoryRepository.find(obj).get(i).getPork());
				testBean.setBeef(toBeChanged);
				testBean.setLamb(inventoryRepository.find(obj).get(i).getLamb());
				testBean.setFish(inventoryRepository.find(obj).get(i).getFish());
				testBean.setChicken(inventoryRepository.find(obj).get(i).getChicken());

				inventoryRepository.update(testBean);

				System.out.println(testBean.toString());

				System.out.println(beef);

				j = i;
				System.out.println(j);

				i = i + 1;
				System.out.println(i);

				if (beef == 0) {
					break;
				}

			}

			return inventoryRepository.find(obj).get(j);

		} else {
			return null;
		}

	}

	public InventoryBean updateLamb(String json) {

		JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String inventoryDate = obj.isNull("inventoryDate") ? null : obj.getString("inventoryDate");
		Integer pork = obj.isNull("pork") ? null : obj.getInt("pork");
		Integer beef = obj.isNull("beef") ? null : obj.getInt("beef");
		Integer lamb = obj.isNull("lamb") ? null : obj.getInt("lamb");
		Integer fish = obj.isNull("fish") ? null : obj.getInt("fish");
		Integer chicken = obj.isNull("chicken") ? null : obj.getInt("chicken");

		if (lamb != null && lamb >= 1) {

			int i = 0;
			int j = 0;
			int toBeChanged;

			while (inventoryRepository.find(obj).get(i) != null) {

				if (inventoryRepository.find(obj).get(i).getLamb() >= lamb) {
					toBeChanged = inventoryRepository.find(obj).get(i).getLamb() - lamb;
					lamb = 0;

				} else {
					toBeChanged = inventoryRepository.find(obj).get(i).getLamb() - inventoryRepository.find(obj).get(i).getLamb();
					lamb = lamb - inventoryRepository.find(obj).get(i).getLamb();

				}

				InventoryBean testBean = new InventoryBean();
				testBean.setId(inventoryRepository.find(obj).get(i).getId());
				testBean.setInventoryDate(inventoryRepository.find(obj).get(i).getInventoryDate());
				testBean.setPork(inventoryRepository.find(obj).get(i).getPork());
				testBean.setBeef(inventoryRepository.find(obj).get(i).getBeef());
				testBean.setLamb(toBeChanged);
				testBean.setFish(inventoryRepository.find(obj).get(i).getFish());
				testBean.setChicken(inventoryRepository.find(obj).get(i).getChicken());

				inventoryRepository.update(testBean);

				System.out.println(testBean.toString());

				System.out.println(lamb);

				j = i;
				System.out.println(j);

				i = i + 1;
				System.out.println(i);

				if (lamb == 0) {
					break;
				}

			}

			return inventoryRepository.find(obj).get(j);

		} else {
			return null;
		}

	}

	public InventoryBean updateFish(String json) {

		JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String inventoryDate = obj.isNull("inventoryDate") ? null : obj.getString("inventoryDate");
		Integer pork = obj.isNull("pork") ? null : obj.getInt("pork");
		Integer beef = obj.isNull("beef") ? null : obj.getInt("beef");
		Integer lamb = obj.isNull("lamb") ? null : obj.getInt("lamb");
		Integer fish = obj.isNull("fish") ? null : obj.getInt("fish");
		Integer chicken = obj.isNull("chicken") ? null : obj.getInt("chicken");

		if (fish != null && fish >= 1) {

			int i = 0;
			int j = 0;
			int toBeChanged;

			while (inventoryRepository.find(obj).get(i) != null) {

				if (inventoryRepository.find(obj).get(i).getFish() >= fish) {
					toBeChanged = inventoryRepository.find(obj).get(i).getFish() - fish;
					fish = 0;

				} else {
					toBeChanged = inventoryRepository.find(obj).get(i).getFish() - inventoryRepository.find(obj).get(i).getFish();
					fish = fish - inventoryRepository.find(obj).get(i).getFish();

				}

				InventoryBean testBean = new InventoryBean();
				testBean.setId(inventoryRepository.find(obj).get(i).getId());
				testBean.setInventoryDate(inventoryRepository.find(obj).get(i).getInventoryDate());
				testBean.setPork(inventoryRepository.find(obj).get(i).getPork());
				testBean.setBeef(inventoryRepository.find(obj).get(i).getBeef());
				testBean.setLamb(inventoryRepository.find(obj).get(i).getLamb());
				testBean.setFish(toBeChanged);
				testBean.setChicken(inventoryRepository.find(obj).get(i).getChicken());

				inventoryRepository.update(testBean);

				System.out.println(testBean.toString());

				System.out.println(fish);

				j = i;
				System.out.println(j);

				i = i + 1;
				System.out.println(i);

				if (fish == 0) {
					break;
				}

			}

			return inventoryRepository.find(obj).get(j);

		} else {
			return null;
		}

	}

	public InventoryBean updateChicken(String json) {

		JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String inventoryDate = obj.isNull("inventoryDate") ? null : obj.getString("inventoryDate");
		Integer pork = obj.isNull("pork") ? null : obj.getInt("pork");
		Integer beef = obj.isNull("beef") ? null : obj.getInt("beef");
		Integer lamb = obj.isNull("lamb") ? null : obj.getInt("lamb");
		Integer fish = obj.isNull("fish") ? null : obj.getInt("fish");
		Integer chicken = obj.isNull("chicken") ? null : obj.getInt("chicken");

		if (chicken != null && chicken >= 1) {

			int i = 0;
			int j = 0;
			int toBeChanged;

			while (inventoryRepository.find(obj).get(i) != null) {

				if (inventoryRepository.find(obj).get(i).getChicken() >= chicken) {
					toBeChanged = inventoryRepository.find(obj).get(i).getChicken() - chicken;
					chicken = 0;

				} else {
					toBeChanged = inventoryRepository.find(obj).get(i).getChicken()
							- inventoryRepository.find(obj).get(i).getChicken();
					chicken = chicken - inventoryRepository.find(obj).get(i).getChicken();

				}

				InventoryBean testBean = new InventoryBean();
				testBean.setId(inventoryRepository.find(obj).get(i).getId());
				testBean.setInventoryDate(inventoryRepository.find(obj).get(i).getInventoryDate());
				testBean.setPork(inventoryRepository.find(obj).get(i).getPork());
				testBean.setBeef(inventoryRepository.find(obj).get(i).getBeef());
				testBean.setLamb(inventoryRepository.find(obj).get(i).getLamb());
				testBean.setFish(inventoryRepository.find(obj).get(i).getFish());
				testBean.setChicken(toBeChanged);

				inventoryRepository.update(testBean);

				System.out.println(testBean.toString());

				System.out.println(chicken);

				j = i;
				System.out.println(j);

				i = i + 1;
				System.out.println(i);

				if (chicken == 0) {
					break;
				}

			}

			return inventoryRepository.find(obj).get(j);

		} else {
			return null;
		}

	}
	
	public List<InventoryBean> find(String json) {											//查詢全部
		try {
			JSONObject obj = new JSONObject(json);
			return inventoryRepository.find(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public long count(String json) {													//計數
		try {
			JSONObject obj = new JSONObject(json);
			return inventoryRepository.count(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
