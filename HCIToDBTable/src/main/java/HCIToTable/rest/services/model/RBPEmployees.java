package HCIToTable.rest.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.tmd.data::Sec.RBPEmployees\"" , schema = "BNS")
@NamedQueries({
    @NamedQuery(name = "RBPEmployees.findAll", query = "SELECT re FROM RBPEmployees re"),
    @NamedQuery(name = "RBPEmployees.findByKey", query = "SELECT re FROM RBPEmployees re WHERE re.grp = :grp AND re.userId = :uId"),
    @NamedQuery(name = "RBPEmployees.updateAll", query = "UPDATE RBPEmployees SET today = :today ,next = :next "),
    @NamedQuery(name = "RBPEmployees.deleteFlagged", query = "DELETE FROM RBPEmployees WHERE today = :today AND next = :next"),
    @NamedQuery(name = "RBPEmployees.deleteTodayFlagged", query = "DELETE FROM RBPEmployees WHERE today = :today"),
})
public class RBPEmployees {
	
	
	@Id
	@Column (name = "\"userId\"" ,columnDefinition = "NVARCHAR(20)")
	private String userId;
	
	@Id
	@Column (name = "\"grp\"" ,columnDefinition = "INTEGER")
	private Integer grp;
	
	@Column (name = "\"today\"" ,columnDefinition = "BOOLEAN default false")
	private Boolean today;
	
	@Column (name = "\"next\"",columnDefinition = "BOOLEAN default true")
	private Boolean next;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getGrp() {
		return grp;
	}
	public void setGrp(Integer grp) {
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

