package pl.helpdesk.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "group_id", nullable=false)
	private UserGroup userGroup;

	@Column(name = "first_name", nullable=false)
	private String firstName;

	@Column(name = "last_name", nullable=false)
	private String lastName;

	@Column(name = "email", nullable=false)
	private String email;

	@OneToMany(mappedBy = "assignedId", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<Task> taskAssigned;

	@OneToMany(mappedBy = "ownerId", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<Task> taskOwned;

	@OneToMany(mappedBy = "authorId", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<Comment> commentsOwned;

	public User() {

	}

	public User(UserGroup userGroup, String firstName, String lastName, String email) {
		this.userGroup = userGroup;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Task> getTaskAssigned() {
		return taskAssigned;
	}

	public void setTaskAssigned(List<Task> taskAssigned) {
		this.taskAssigned = taskAssigned;
	}

	public List<Task> getTaskOwned() {
		return taskOwned;
	}

	public void setTaskOwned(List<Task> taskOwned) {
		this.taskOwned = taskOwned;
	}

	public List<Comment> getCommentsOwned() {
		return commentsOwned;
	}

	public void setCommentsOwned(List<Comment> commentsOwned) {
		this.commentsOwned = commentsOwned;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userGroup=" + userGroup + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}

}
