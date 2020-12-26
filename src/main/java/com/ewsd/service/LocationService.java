package com.ewsd.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ewsd.config.persistence.HibernateConfig;
import com.ewsd.exceptions.ResourceNotFoundException;
import com.ewsd.model.Location;

import com.ewsd.repositories.LocationRepository;

@Service
public class LocationService extends HibernateConfig<Location>{
	@Autowired
    private LocationRepository locationRepository;
    private HibernateConfig hibernateConfig;

    public LocationService(HibernateConfig hibernateConfigy) {
    	this.hibernateConfig = hibernateConfig;
     //   this.locationRepository = locationRepository;
    }


 
    public boolean add(Location locationEntity) {
    	
		if(!exists(locationEntity.getLocationName())) {
			
			locationRepository.save(locationEntity);
			return true;
		}else {
			return false;
		}
	}
	
	
	private boolean exists(String locationName) {
		if(getLocationByLocationName(locationName)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	public Location getLocationByLocationName(String locationName) {
		 return locationRepository.findByLocationName(locationName); 
	}
	
    public void edit(Location location) {
    	locationRepository.save(location);
    }
    public List<Location> getAll() {
		return (List<Location>) locationRepository.findAll();
	}

	public Location getById(long locationId) {

		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<Location> cq = cb.createQuery(Location.class);
		Root<Location> root = cq.from(Location.class);
		cq.where(cb.equal(root.get("id"), locationId));
		
		// perform query
		var ac_list = getListFromQuery(cq);

		return Optional.ofNullable(ac_list.get(0))
				.orElseThrow(() -> new ResourceNotFoundException("Location Not Found With Thid Id"));
	}

	
	public boolean delete(Location location) {
		// TODO Auto-generated method stub
		locationRepository.delete(location);
		return true;
	}
	public Location findById(Long id) {
		// TODO Auto-generated method stub
		return locationRepository.getOne(id);
	}

}
