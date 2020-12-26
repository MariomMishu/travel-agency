package com.ewsd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ewsd.model.Attachment;
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long>{
	@Query("SELECT t.fileName FROM Attachment t where t.id = :id") 
   Attachment findFileNameById(@Param("id") Long id);
}
