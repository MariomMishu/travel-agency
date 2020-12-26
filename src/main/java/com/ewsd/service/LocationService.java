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
public class LocationService {
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
		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if (!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		System.out.println(locationId);
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);
		Root<Location> root = criteriaQuery.from(Location.class);
		criteriaQuery.select(root);
		criteriaQuery.where(
				criteriaBuilder.and(
						criteriaBuilder.equal(root.get("id"), locationId),
						criteriaBuilder.isTrue(root.get("isDelete"))
				)
		);
		var query = session.getEntityManagerFactory().createEntityManager().createQuery(criteriaQuery);
		var location_list = query.getResultList();
		System.out.println("Location");
		System.out.println(location_list);
		return Optional.ofNullable(location_list.get(0))
				.orElseThrow(() -> new ResourceNotFoundException("No Location was found with this ID!"));
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
