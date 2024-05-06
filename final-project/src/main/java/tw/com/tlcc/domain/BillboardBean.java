package tw.com.tlcc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "billboard")
public class BillboardBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="alias", columnDefinition = "nvarchar")
	private String alias;
	@Column(name="content", columnDefinition = "nvarchar")
	private String content;
	@Column(name="message_time", columnDefinition = "datetime")
	private java.util.Date messageTime;
	@Column(name="push_number")
	private Integer pushNumber;
	@Column(name="you_suck_number")
	private Integer youSuckNumber;
	@Column(name="view_count")
	private Integer viewCount;
	
	@Override
	public String toString() {
		return "BillboardBean [id=" + id + ", alias=" + alias + ", content=" + content + ", messageTime=" + messageTime
				+ ", pushNumber=" + pushNumber + ", youSuckNumber=" + youSuckNumber + ", viewCount=" + viewCount + "]" + "\n";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	public java.util.Date getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(java.util.Date messageTime) {
		this.messageTime = messageTime;
	}
	public Integer getPushNumber() {
		return pushNumber;
	}
	public void setPushNumber(Integer pushNumber) {
		this.pushNumber = pushNumber;
	}
	public Integer getYouSuckNumber() {
		return youSuckNumber;
	}
	public void setYouSuckNumber(Integer youSuckNumber) {
		this.youSuckNumber = youSuckNumber;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	
	
}
