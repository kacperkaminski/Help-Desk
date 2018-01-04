package pl.helpdesk.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_group")
public class UserGroup {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;
	
	@OneToMany(mappedBy="userGroup", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<User> usersGroup;

	public UserGroup() {
	}
	
	public UserGroup(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<User> getUsers() {
		return usersGroup;
	}

	public void setUsers(List<User> users) {
		this.usersGroup = users;
	}

	public List<User> getUsersGroup() {
		return usersGroup;
	}

	public void setUsersGroup(List<User> usersGroup) {
		this.usersGroup = usersGroup;
	}

	@Override
	public String toString() {
		return "UserGroup [id=" + id + ", title=" + title + ", users=" + usersGroup + "]";
	}
	
}
