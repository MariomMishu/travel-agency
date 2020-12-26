package com.ewsd.dto;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.ewsd.model.Location;
import com.ewsd.model.User;

public class PostDto  implements Serializable {
	private Long id;

	private String title;

	private String status;

	private Location location;

	private Boolean isDelete;

    private String visibility;
	private User userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	

	public PostDto(Long id, String title, String status, Location location, Boolean isDelete,
			 String visibility, User userId) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.location = location;
		this.isDelete = isDelete;
	
		this.visibility = visibility;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "PostDto [id=" + id + ", title=" + title + ", status=" + status + ", location=" + location
				+ ", isDelete=" + isDelete + ", visibility=" + visibility + ", userId="
				+ userId + "]";
	}

	public PostDto() {
		super();
	}

	
	

}
