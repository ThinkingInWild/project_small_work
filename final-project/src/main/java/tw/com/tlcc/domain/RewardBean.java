package tw.com.tlcc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reward")
public class RewardBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private  Integer id;
	
	@Column(name="member_id")
	private String memberId;
	
	@Column(name="order_number")
	private Integer orderNumber;
	
	@Column(name="received_date",columnDefinition = "datetime")
	private java.util.Date receivedDate;
	
	@Column(name="received_points")
	private Integer receivedPoints;
	
	@Column(name="used_date",columnDefinition = "datetime")
	private java.util.Date usedDate;
	
	@Column(name="used_points")
	private Integer usedPoints;
	
	@Column(name="current_points")
	private Integer currentPoints;   

	@Override
	public String toString() {
		return "RewardBean [id=" + id + ", memberId=" + memberId + ", orderNumber=" + orderNumber + ", receivedDate="
				+ receivedDate + ", receivedPoints=" + receivedPoints + ", usedDate=" + usedDate + ", usedPoints="
				+ usedPoints + ", currentPoints=" + currentPoints + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public java.util.Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(java.util.Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Integer getReceivedPoints() {
		return receivedPoints;
	}

	public void setReceivedPoints(Integer receivedPoints) {
		this.receivedPoints = receivedPoints;
	}

	public java.util.Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(java.util.Date usedDate) {
		this.usedDate = usedDate;
	}

	public Integer getUsedPoints() {
		return usedPoints;
	}

	public void setUsedPoints(Integer usedPoints) {
		this.usedPoints = usedPoints;
	}

	public Integer getCurrentPoints() {
		return currentPoints;
	}

	public void setCurrentPoints(Integer currentPoints) {
		this.currentPoints = currentPoints;
	}


	
	
	
	

	
	
	
	
	
	

}
