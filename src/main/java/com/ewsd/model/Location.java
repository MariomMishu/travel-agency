package com.ewsd.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 20, nullable = false)
    private Long id;
    @Column
    private String locationName;
    @Column(name = "is_delete")
	private Boolean isDelete;
	
	@ManyToOne
	@JoinColumn(name = "e_by")
	private User entryBy;
	
	@Column(name = "e_date")
	private LocalDateTime entryDate;
	
	@ManyToOne
	@JoinColumn(name = "u_by")
	private User updateBy;
	
	@Column(name = "u_date")
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

	public User getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(User entryBy) {
		this.entryBy = entryBy;
	}

	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public Location(Long id, String locationName, Boolean isDelete, User entryBy, LocalDateTime entryDate,
			User updateBy, LocalDateTime updateDate) {
		super();
		this.id = id;
		this.locationName = locationName;
		this.isDelete = isDelete;
		this.entryBy = entryBy;
		this.entryDate = entryDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
	}

	public Location() {
		super();
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", locationName=" + locationName + ", isDelete=" + isDelete + ", entryBy="
				+ entryBy + ", entryDate=" + entryDate + ", updateBy=" + updateBy + ", updateDate=" + updateDate + "]";
	}


}
