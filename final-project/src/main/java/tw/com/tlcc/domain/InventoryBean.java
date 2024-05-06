package tw.com.tlcc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class InventoryBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "inventory_date", columnDefinition = "date")
	private java.util.Date inventoryDate;
	@Column(name = "pork")
	private Integer pork;
	@Column(name = "beef")
	private Integer beef;
	@Column(name = "lamb")
	private Integer lamb;
	@Column(name = "fish")
	private Integer fish;
	@Column(name = "chicken")
	private Integer chicken;
	
	@Override
	public String toString() {
		return "InventoryBean [id=" + id + ", inventoryDate=" + inventoryDate + ", pork=" + pork + ", beef=" + beef
				+ ", lamb=" + lamb + ", fish=" + fish + ", chicken=" + chicken + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.util.Date getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(java.util.Date inventoryDate) {
		this.inventoryDate = inventoryDate;
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

	public Integer getChicken() {
		return chicken;
	}

	public void setChicken(Integer chicken) {
		this.chicken = chicken;
	}
	
	
	
	
	
	
	
}
