package pl.helpdesk.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "owner_id")
	private User ownerId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "assigned_id")
	private User assignedId;

	@Column(name = "title")
	private String title;
	
	@OneToMany(mappedBy="taskId", cascade = {CascadeType.ALL})
	private List<Comment> comments;
	
	public Task() {

	}

	public Task(User ownerId, User assignedId, String title, String message) {
		this.ownerId = ownerId;
		this.assignedId = assignedId;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(User ownerId) {
		this.ownerId = ownerId;
	}

	public User getAssignedId() {
		return assignedId;
	}

	public void setAssignedId(User assignedId) {
		this.assignedId = assignedId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Comment> getUpdates() {
		return comments;
	}

	public void setUpdates(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", ownerId=" + ownerId + ", assignedId=" + assignedId + ", title=" + title + "]";
	}

}
