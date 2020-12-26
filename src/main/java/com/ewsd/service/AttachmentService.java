package com.ewsd.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ewsd.config.persistence.HibernateConfig;
import com.ewsd.model.Attachment;
import com.ewsd.repositories.AttachmentRepository;
import com.ewsd.util.Constants;

@Service
public class AttachmentService {

	@Autowired
	private AttachmentRepository attachmentRepository;
	private HibernateConfig hibernateConfig;
	
	public AttachmentService(HibernateConfig hibernateConfig) {
		this.hibernateConfig = hibernateConfig;
	}
	// Save the uploaded file to this folder
	String absoluteFilePath = Constants.UPLOADED_FOLDER;
	private Logger logger = Logger.getLogger(AttachmentService.class.getName());

	public Attachment save(Attachment attachment, MultipartFile file, Long userId) {
		try {
			
			byte[] bytes = file.getBytes();

			String absoluteFilePath = Constants.UPLOADED_FOLDER;
			File dir = Paths.get(absoluteFilePath).toFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String extension = "";
			StringTokenizer tokenizer = new StringTokenizer(file.getOriginalFilename(), ".");
			while (tokenizer.hasMoreTokens()) {
				extension = tokenizer.nextToken();
			}
			String url = dir.getAbsolutePath() + userId + attachment.getFileName() + "." + extension;
			File serverFile = new File(url);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			url = "/uploaded_img/" + userId + attachment.getFileName() + "." + extension;
			attachment.setFileURL(url);
			attachment.setFileType(Files.probeContentType(Paths.get(url)));
			// Get the file and save it somewhere
		
		} catch (IOException e) {
			e.printStackTrace();
		}

		attachmentRepository.save(attachment);
		return attachment;
	}
	public Attachment getAttachmentById(Long id) {
		
		System.out.println(attachmentRepository.findFileNameById(id));
		return attachmentRepository.findFileNameById(id); 
		}
	
}
