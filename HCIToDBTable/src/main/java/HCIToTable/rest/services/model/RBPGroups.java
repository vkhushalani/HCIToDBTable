package HCIToTable.rest.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.tmd.data::Sec.RBPGroups\"", schema = "BNS")
@NamedQueries({
    @NamedQuery(name = "RBPGroups.findAll", query = "SELECT re FROM RBPGroups re"),
    @NamedQuery(name = "RBPGroups.findByKey", query = "SELECT re FROM RBPGroups re WHERE re.grp = :grp AND re.userId = :uId AND re.role = :role"),
    @NamedQuery(name = "RBPGroups.updateAll", query = "UPDATE RBPGroups SET today = :today ,next = :next "),
    @NamedQuery(name = "RBPGroups.deleteFlagged", query = "DELETE FROM RBPGroups WHERE today = :today AND next = :next"),
    @NamedQuery(name = "RBPGroups.deleteTodayFlagged", query = "DELETE FROM RBPGroups WHERE today = :today"),
})
public class RBPGroups {
	
	@Id
	@Column (name = "\"userId\"",columnDefinition = "NVARCHAR(20)")
	private String userId;
	
	@Id
	@Column (name = "\"role\"",columnDefinition = "NVARCHAR(1)")
	private String role;
	
	@Id
	@Column (name = "\"grp\"",columnDefinition = "INTEGER")
	private int grp;
	
	@Column (name = "\"today\"",columnDefinition = "BOOLEAN default false")
	private Boolean today;
	
	@Column (name = "\"next\"",columnDefinition = "BOOLEAN default true")
	private Boolean next;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getGrp() {
		return grp;
	}
	public void setGrp(int grp) {
		this.grp = grp;
	}
	public Boolean getToday() {
		return today;
	}
	public void setToday(Boolean today) {
		this.today = today;
	}
	public Boolean getNext() {
		return next;
	}
	public void setNext(Boolean next) {
		this.next = next;
	}
	

}
