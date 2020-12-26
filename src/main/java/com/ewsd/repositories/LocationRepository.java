package com.ewsd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.ewsd.model.Location;

@Repository
@Transactional
public interface LocationRepository  extends JpaRepository<Location, Long>{
	
	 Location findByLocationName(String locationName);
	
}
