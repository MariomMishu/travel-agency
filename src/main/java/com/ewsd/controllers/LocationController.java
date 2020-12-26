package com.ewsd.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ewsd.dto.LocationDto;
import com.ewsd.model.Location;
import com.ewsd.request_models.LocationRm;
import com.ewsd.service.LocationService;
import com.ewsd.service.UserService;
import com.ewsd.util.Util;

@Controller
public class LocationController {
	@Autowired
	private LocationService locationService;

	@Autowired
	UserService userService;

	@GetMapping("/location/add")
	public String add_GET(Model model, Authentication authentication) {
		var userName = authentication.getName();
		org.springframework.security.core.userdetails.User authenticateduser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		com.ewsd.model.User user = userService.getUserByName(authenticateduser.getUsername());
		model.addAttribute("user", user);
		model.addAttribute("username", userName);
		model.addAttribute("locationRm", new LocationRm());
		//model.addAttribute("dept_list", departmentService.getAll());
		return "location/add";
	}

	@PostMapping("/location/add")
	public String add(Model model, @ModelAttribute("locationRm") LocationRm locationRm, Authentication authentication) {
		LocalDateTime entry_date = LocalDateTime.now();
		 org.springframework.security.core.userdetails.User authenticateduser  = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 com.ewsd.model.User user = userService.getUserByName(authenticateduser.getUsername());
		 
		LocationDto locationDto = new LocationDto();
		Location locEntity = new Location();
		
		locationDto.setLocationName(locationRm.getLocationName());
		
		BeanUtils.copyProperties(locationDto,locEntity);
		locEntity.setEntryDate(entry_date);
		locEntity.setIsDelete(true);
		locEntity.setEntryBy(user);
		locationService.add(locEntity);
		model.addAttribute("message", "New Location Added Successfully");
		return "redirect:/location/show-all";
	}

	@GetMapping("/location/show-all")
	public String show_all(Model model, Authentication authentication) {
		var userName = authentication.getName();
		org.springframework.security.core.userdetails.User authenticateduser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		com.ewsd.model.User user = userService.getUserByName(authenticateduser.getUsername());
		model.addAttribute("user", user);
		model.addAttribute("username", userName);
		// model.addAttribute("deptName",);
		model.addAttribute("location", new LocationRm());
		model.addAttribute("location_list", locationService.getAll());
		model.addAttribute("message", "Showing All location");
		return "location/show-all";
	}
}
