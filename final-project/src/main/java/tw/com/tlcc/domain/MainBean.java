package tw.com.tlcc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="main")
public class MainBean {
	
	@Id
	@Column(name = "main_id")
	private String mainId;
	
	@Column(name = "main_name", columnDefinition="nvarchar")
	private String mainName;
	
	@Column(name = "main_price")
	private Integer mainPrice;
	
	@Column(name = "extra_main_price")
	private Integer extraMainPrice;

	@Override
	public String toString() {
		return "MainBean [mainId=" + mainId + ", mainName=" + mainName + ", mainPrice=" + mainPrice
				+ ", extraMainPrice=" + extraMainPrice + "]";
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getMainName() {
		return mainName;
	}

	public void setMainName(String mainName) {
		this.mainName = mainName;
	}

	public Integer getMainPrice() {
		return mainPrice;
	}

	public void setMainPrice(Integer mainPrice) {
		this.mainPrice = mainPrice;
	}

	public Integer getExtraMainPrice() {
		return extraMainPrice;
	}

	public void setExtraMainPrice(Integer extraMainPrice) {
		this.extraMainPrice = extraMainPrice;
	}
	
	
	
	
}
