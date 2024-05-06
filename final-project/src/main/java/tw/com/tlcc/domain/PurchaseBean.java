package tw.com.tlcc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="purchase")
public class PurchaseBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "order_number")
	private Integer orderNumber;
	
	@Column(name = "member_id")
	private String memberId;
	
	@Column(name = "order_address" , columnDefinition="nvarchar")
	private String orderAddress;
	
	@Column(name = "order_date" ,columnDefinition="date")
	private java.util.Date orderDate;
	
	@Column(name = "pickup_date" ,columnDefinition="date")
	private java.util.Date pickupDate;
	
	@Column(name = "order_total")
	private Integer orderTotal;
	
	@Column(name = "chicken")
	private Integer chicken;
	
	@Column(name = "pork")
	private Integer pork;
	
	@Column(name = "beef")
	private Integer beef;
	
	@Column(name = "lamb")
	private Integer lamb;
	
	@Column(name = "fish")
	private Integer fish;
	
	@Column(name = "extra_chicken")
	private Integer extraChicken;
	
	@Column(name = "extra_pork")
	private Integer extraPork;
	
	@Column(name = "extra_beef")
	private Integer extraBeef;
	
	@Column(name = "extra_lamb")
	private Integer extraLamb;
	
	@Column(name = "extra_fish")
	private Integer extraFish;

	@Override
	public String toString() {
		return "PurchaseBean [id=" + id + ", orderNumber=" + orderNumber + ", memberId=" + memberId + ", orderAddress="
				+ orderAddress + ", orderDate=" + orderDate + ", pickupDate=" + pickupDate + ", orderTotal="
				+ orderTotal + ", chicken=" + chicken + ", pork=" + pork + ", beef=" + beef + ", lamb=" + lamb
				+ ", fish=" + fish + ", extraChicken=" + extraChicken + ", extraPork=" + extraPork + ", extraBeef="
				+ extraBeef + ", extraLamb=" + extraLamb + ", extraFish=" + extraFish + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public java.util.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}

	public java.util.Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(java.util.Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Integer getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Integer orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Integer getChicken() {
		return chicken;
	}

	public void setChicken(Integer chicken) {
		this.chicken = chicken;
	}

	public Integer getPork() {
		return pork;
	}

	public void setPork(Integer pork) {
		this.pork = pork;
	}

	public Integer getBeef() {
		return beef;
	}

	public void setBeef(Integer beef) {
		this.beef = beef;
	}

	public Integer getLamb() {
		return lamb;
	}

	public void setLamb(Integer lamb) {
		this.lamb = lamb;
	}

	public Integer getFish() {
		return fish;
	}

	public void setFish(Integer fish) {
		this.fish = fish;
	}

	public Integer getExtraChicken() {
		return extraChicken;
	}

	public void setExtraChicken(Integer extraChicken) {
		this.extraChicken = extraChicken;
	}

	public Integer getExtraPork() {
		return extraPork;
	}

	public void setExtraPork(Integer extraPork) {
		this.extraPork = extraPork;
	}

	public Integer getExtraBeef() {
		return extraBeef;
	}

	public void setExtraBeef(Integer extraBeef) {
		this.extraBeef = extraBeef;
	}

	public Integer getExtraLamb() {
		return extraLamb;
	}

	public void setExtraLamb(Integer extraLamb) {
		this.extraLamb = extraLamb;
	}

	public Integer getExtraFish() {
		return extraFish;
	}

	public void setExtraFish(Integer extraFish) {
		this.extraFish = extraFish;
	}
	
	
}
