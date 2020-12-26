package com.ewsd.controllers;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.ewsd.dto.PostDto;
import com.ewsd.enums.Visibility;
import com.ewsd.model.Post;
import com.ewsd.request_models.PostRm;
import com.ewsd.service.LocationService;
import com.ewsd.service.PostService;
import com.ewsd.service.UserService;

@Controller
public class PostController {
	@Autowired
	UserService userService;
	@Autowired
	LocationService locationService;
	@Autowired
	private PostService postService;
	
	@GetMapping("/post/add")
	public String AddnewPost_GET(Model model, Authentication authentication) {

		var userName = authentication.getName();
		org.springframework.security.core.userdetails.User authenticateduser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		com.ewsd.model.User user = userService.getUserByName(authenticateduser.getUsername());
		model.addAttribute("user", user);
		model.addAttribute("username", userName);
		List<String> visibilities = new ArrayList<>();
        visibilities.add(Visibility.valueOf(Visibility.PUBLIC));
        visibilities.add(Visibility.valueOf(Visibility.PRIVATE));
        model.addAttribute("visibilities", visibilities);
		model.addAttribute("postRm", new PostRm());
		model.addAttribute("location_list", locationService.getAll());
		return "/post/add";
	}

	@PostMapping("/post/add")
	public String postNewIdea_POST(Model model, @ModelAttribute("postRm") PostRm postRm, Authentication authentication,
			@RequestParam("images[]") MultipartFile[] files) {
		LocalDateTime entry_date = LocalDateTime.now();
		org.springframework.security.core.userdetails.User authenticateduser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		com.ewsd.model.User user = userService.getUserByName(authenticateduser.getUsername());
		System.out.println(postRm.getLocationId());
		var locationId = locationService.getById(postRm.getLocationId());
		//System.out.println(locationId);
		PostDto postDto = new PostDto();
		Post postEntity = new Post();
		postDto.setTitle(postRm.getTitle());
		postDto.setStatus(postRm.getStatus());
		postDto.setLocation(locationId);
		postDto.setVisibility(postRm.getVisibility());
		
		BeanUtils.copyProperties(postDto, postEntity);
		
		postEntity.setEntryDate(entry_date);
		postEntity.setIsDelete(true);
		
		postEntity.setUserId(user);
		postService.add(postEntity);
		return "redirect:/post/show-all";
	}

	@GetMapping("/post/show-all")
	public String show_all(Model model, Authentication authentication) {
		var userName = authentication.getName();
		org.springframework.security.core.userdetails.User authenticateduser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		com.ewsd.model.User user = userService.getUserByName(authenticateduser.getUsername());
		model.addAttribute("user", user);
		model.addAttribute("username", userName);
		model.addAttribute("post", new PostRm());
		model.addAttribute("post_list",postService.getAll());
		model.addAttribute("message", "Showing All Post");  
		return "post/show-all";
	}

	
	  @GetMapping("/post/timeline") 
	  public String view_posts(Model model,Authentication authentication) { 
		  var userName = authentication.getName();
	  org.springframework.security.core.userdetails.User authenticateduser =(org.springframework.security.core.userdetails.User) SecurityContextHolder
	  .getContext().getAuthentication().getPrincipal(); 
	  com.ewsd.model.User user = userService.getUserByName(authenticateduser.getUsername());
	 
	  List<PostDto> allpost = postService.getAllPostDtoWithUserId(user.getId());
	  System.out.println(allpost);
	  model.addAttribute("user", user); model.addAttribute("username", userName);
	  
	  model.addAttribute("post_list", allpost); 
	  model.addAttribute("message", "Showing All Post By User");
	  return "post/timeline"; }
	 
	@GetMapping("/post/view_all_posts")
	public String view_all_posts(Model model, Authentication authentication) {
		var userName = authentication.getName();
		org.springframework.security.core.userdetails.User authenticateduser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		com.ewsd.model.User user = userService.getUserByName(authenticateduser.getUsername());
		  
	        String dateTimeString = "";
	        for (Post postEntity : postService.getAll()) {
			  dateTimeString = convertTimestampToString(postEntity.getEntryDate(), "d/MM/YYYY hh:mm:ss aaa");
            String time = dateTimeString.split(" ")[1] + " " + dateTimeString.split(" ")[2];

	        }
	     //   String visibility ="Public";
	   // List<PostDto> allPost = postService.getAll(visibility);
	    model.addAttribute("user", user);
		model.addAttribute("username", userName);
		//model.addAttribute("post_list", allPost);
		model.addAttribute("post_list", postService.getAll());
		model.addAttribute("message", "Showing All Posts");
		return "post/view_all_posts";
	}

	  public String convertTimestampToString(LocalDateTime timestamp, String format) {
	        Date date = new Date();
	        date.setTime(Timestamp.valueOf(LocalDateTime.now()).getTime());
	        String formattedDate = new SimpleDateFormat(format).format(date);
	        return formattedDate;
	    }

	  @GetMapping("/post/edit")
		public String edit_GET(Model model, @RequestParam("id") long id, Authentication authentication) {
			var userName = authentication.getName();
			org.springframework.security.core.userdetails.User authenticateduser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			com.ewsd.model.User user = userService.getUserByName(authenticateduser.getUsername());
			model.addAttribute("user", user);
			model.addAttribute("username", userName);

			var postDto = postService.getById(id);
		
		  var postRm = new PostRm(); 
		  BeanUtils.copyProperties(postDto, postRm);
		  postRm.setLocationId(postDto.getLocation().getId());
		  postRm.setLocationName(postDto.getLocation().getLocationName());
		  postRm.setTitle(postDto.getTitle()); 
		  postRm.setStatus(postDto.getStatus());
		  postRm.setVisibility(postDto.getVisibility());
		  List<String> visibilities = new ArrayList<>();
	        visibilities.add(Visibility.valueOf(Visibility.PUBLIC));
	        visibilities.add(Visibility.valueOf(Visibility.PRIVATE));
	        model.addAttribute("visibilities", visibilities);
			model.addAttribute("postRm", postRm);
			model.addAttribute("location_list", locationService.getAll());
			return "post/edit";
		}
	  @PostMapping("/post/edit")
		public String edit(Model model, @ModelAttribute("postRm") PostRm postRm, Authentication authentication) {
			LocalDateTime update_date = LocalDateTime.now();
			 org.springframework.security.core.userdetails.User authenticateduser  = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 com.ewsd.model.User user = userService.getUserByName(authenticateduser.getUsername());
			 
			
			var locationId = locationService.getById(postRm.getLocationId());
			System.out.println(locationId);
			//CategoryDto catDto = new CategoryDto();
			Post postEntity = new Post();
			var postDto = postService.getById(postRm.getId());
			
			postDto.setTitle(postRm.getTitle());
			postDto.setStatus(postRm.getStatus());
			postDto.setLocation(locationId);
			postDto.setVisibility(postRm.getVisibility());
			
			BeanUtils.copyProperties(postDto, postEntity);
			postEntity.setIsDelete(true);
			postEntity.setUserId(user);
			
			postEntity.setEntryDate(postDto.getEntryDate());
			postEntity.setUpdateDate(update_date);
			postEntity.setIsDelete(true);
			
			postService.edit(postEntity);
			model.addAttribute("message", "Post Edited Successfully");
			return "redirect:/post/timeline";
		}


}
