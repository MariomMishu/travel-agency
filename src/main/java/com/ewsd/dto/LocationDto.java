package com.ewsd.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LocationDto  implements Serializable {
	private Long id;
    private String locationName;
    private Boolean isDelete;
    private LocalDateTime entryDate;
    private LocalDateTime updateDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public LocationDto(Long id, String locationName, Boolean isDelete, LocalDateTime entryDate,
			LocalDateTime updateDate) {
		super();
		this.id = id;
		this.locationName = locationName;
		this.isDelete = isDelete;
		this.entryDate = entryDate;
		this.updateDate = updateDate;
	}
	public LocationDto() {
		super();
	}
	@Override
	public String toString() {
		return "LocationDto [id=" + id + ", locationName=" + locationName + ", isDelete=" + isDelete + ", entryDate="
				+ entryDate + ", updateDate=" + updateDate + "]";
	}
    
}
