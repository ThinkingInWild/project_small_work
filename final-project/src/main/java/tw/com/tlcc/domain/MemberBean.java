package tw.com.tlcc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "member")
public class MemberBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="member_id")
	private String memberId;
	@Column(name="member_password")
	private String memberPassword;
	@Column(name="member_name", columnDefinition = "nvarchar")
	private String memberName;
	@Column(name="email")
	private String email;
	@Column(name="gender")
	private Integer gender;
	@Column(name="member_address", columnDefinition = "nvarchar")
	private String memberAddress;
	@Column(name="discount_points_left")
	private Integer discountPointsLeft;
	@Column(name="sign_up_date", columnDefinition = "date")
	private java.util.Date signUpDate;
	@Column(name="final_log_in_date", columnDefinition = "date")
	private java.util.Date finalLogInDate;
	@Column(name="access_level")
	private String accessLevel;
	@Column(name="coupon_quantity")
	private Integer couponQuantity;
	
	@Override
	public String toString() {
		return "MemberBean [id=" + id + ", memberId=" + memberId + ", memberPassword=" + memberPassword
				+ ", memberName=" + memberName + ", email=" + email + ", gender=" + gender + ", memberAddress="
				+ memberAddress + ", discountPointsLeft=" + discountPointsLeft + ", signUpDate=" + signUpDate
				+ ", finalLogInDate=" + finalLogInDate + ", accessLevel=" + accessLevel + ", couponQuantity="
				+ couponQuantity + "]" + "\n";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		if(id!=null) {
		this.id = id;
		}
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public Integer getDiscountPointsLeft() {
		return discountPointsLeft;
	}
	public void setDiscountPointsLeft(Integer discountPointsLeft) {
		this.discountPointsLeft = discountPointsLeft;
	}
	public java.util.Date getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(java.util.Date signUpDate) {
		this.signUpDate = signUpDate;
	}
	public java.util.Date getFinalLogInDate() {
		return finalLogInDate;
	}
	public void setFinalLogInDate(java.util.Date finalLogInDate) {
		this.finalLogInDate = finalLogInDate;
	}
	public String getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
	public Integer getCouponQuantity() {
		return couponQuantity;
	}
	public void setCouponQuantity(Integer couponQuantity) {
		this.couponQuantity = couponQuantity;
	}
	
	
}
