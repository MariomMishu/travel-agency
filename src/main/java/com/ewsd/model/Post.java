package com.ewsd.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 20, nullable = false)
    private Long id;
    @Column
    private String status;
    @Column
    private String title;
    @ManyToOne
	private Location location;
    
    @Column
    private String visibility;
    @Column(name = "is_delete")
	private Boolean isDelete;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User userId;

	@Column(name = "e_date")
	private LocalDateTime entryDate;

	@Column(name = "u_date")
	private LocalDateTime updateDate;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public LocalDateTime getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	public Post() {
		super();
	}
	public Post(Long id, String status, String title, User owner, List<String> locations, Location location,
			 String visibility, Boolean isDelete, User userId, LocalDateTime entryDate,
			LocalDateTime updateDate) {
		super();
		this.id = id;
		this.status = status;
		this.title = title;
		this.location = location;
		
		this.visibility = visibility;
		this.isDelete = isDelete;
		this.userId = userId;
		this.entryDate = entryDate;
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", status=" + status + ", title=" + title + ", location=" + location + ",visibility=" + visibility
				+ ", isDelete=" + isDelete + ", userId=" + userId + ", entryDate=" + entryDate + ", updateDate="
				+ updateDate + "]";
	}
	
    
    
   }
