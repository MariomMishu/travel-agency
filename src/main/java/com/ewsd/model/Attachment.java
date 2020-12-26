package com.ewsd.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_attachment")
public class Attachment implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id", length = 20, nullable = false)
	private Long id;
	
	@Column(name="file_name", nullable = false)
	private String fileName;
	
	@Column(name="file_title", nullable = true)
	private String fileTitle;
	
	@Column(name="file_url", nullable = false)
	private String fileURL;
	
	@Column(name="file_type")
	private String fileType;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Attachment(Long id, String fileName, String fileTitle, String fileURL, String fileType) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileTitle = fileTitle;
		this.fileURL = fileURL;
		this.fileType = fileType;
	
	}

	public Attachment() {
		super();
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", fileName=" + fileName + ", fileTitle=" + fileTitle + ", fileURL=" + fileURL
				+ ", fileType=" + fileType + "]";
	}

}
