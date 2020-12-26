package com.ewsd.request_models;

import java.io.Serializable;

import com.ewsd.model.User;

public class PostRm  implements Serializable {
	private Long id;
	
	private String title;
	
	private String status;
	
	private Long locationId;
	
	private String locationName;
    private String visibility;
	
	private Boolean isDelete;
	
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

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
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

	public PostRm(Long id, String title, String status, Long locationId, String locationName, String visibility,
			 Boolean isDelete, User userId) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.locationId = locationId;
		this.locationName = locationName;
		this.visibility = visibility;
		this.isDelete = isDelete;
		this.userId = userId;
	}

	public PostRm() {
		super();
	}

	@Override
	public String toString() {
		return "PostRm [id=" + id + ", title=" + title + ", status=" + status + ", locationId=" + locationId
				+ ", locationName=" + locationName + ", visibility=" + visibility
				+ ", isDelete=" + isDelete + ", userId=" + userId + "]";
	}



	
}
