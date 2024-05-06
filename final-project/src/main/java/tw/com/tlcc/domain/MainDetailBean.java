package tw.com.tlcc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="main_detail")
public class MainDetailBean {

	
	//以下是ＭainBean
	@Id
	@Column(name = "main_id")
	private String mainId;
	
	@Column(name = "main_name", columnDefinition="nvarchar")
	private String mainName;
	
	@Column(name = "main_price")
	private Integer mainPrice;
	
	@Column(name = "extra_main_price")
	private Integer extraMainPrice;
	
	
	//以下是DetailBean
	@Column(name = "order_number")
	private Integer orderNumber;
	
	@Column(name = "main", columnDefinition = "nvarchar")
	private String main;
	
	@Column(name = "side1", columnDefinition = "nvarchar")
	private String side1;
	
	@Column(name = "side2", columnDefinition = "nvarchar")
	private String side2;
	
	@Column(name = "side3", columnDefinition = "nvarchar")
	private String side3;
	
	@Column(name = "side4", columnDefinition = "nvarchar")
	private String side4;
	
	@Column(name = "extra_main", columnDefinition = "nvarchar")
	private String extraMain;
	
	@Column(name = "detail_total")
	private Integer detailTotal;

	@Override
	public String toString() {
		return "MainDetailBean [mainId=" + mainId + ", mainName=" + mainName + ", mainPrice=" + mainPrice
				+ ", extraMainPrice=" + extraMainPrice + ", orderNumber=" + orderNumber + ", main=" + main + ", side1="
				+ side1 + ", side2=" + side2 + ", side3=" + side3 + ", side4=" + side4 + ", extraMain=" + extraMain
				+ ", detailTotal=" + detailTotal + "]";
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

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getSide1() {
		return side1;
	}

	public void setSide1(String side1) {
		this.side1 = side1;
	}

	public String getSide2() {
		return side2;
	}

	public void setSide2(String side2) {
		this.side2 = side2;
	}

	public String getSide3() {
		return side3;
	}

	public void setSide3(String side3) {
		this.side3 = side3;
	}

	public String getSide4() {
		return side4;
	}

	public void setSide4(String side4) {
		this.side4 = side4;
	}

	public String getExtraMain() {
		return extraMain;
	}

	public void setExtraMain(String extraMain) {
		this.extraMain = extraMain;
	}

	public Integer getDetailTotal() {
		return detailTotal;
	}

	public void setDetailTotal(Integer detailTotal) {
		this.detailTotal = detailTotal;
	}
	
	
	
	
}
