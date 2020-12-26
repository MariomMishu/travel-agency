package com.ewsd.controllers;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

import com.ewsd.dto.LocationDto;
import com.ewsd.dto.PostDto;
import com.ewsd.enums.Visibility;
import com.ewsd.model.Attachment;
import com.ewsd.model.Post;
import com.ewsd.request_models.PostRm;
import com.ewsd.service.AttachmentService;
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
	@Autowired
	private AttachmentService attachmentService;

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

		var locationId = locationService.getById(postRm.getLocationId());
		System.out.println(locationId);
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
	@GetMapping("/post/view_all_posts")
	public String view_all_ideas(Model model, Authentication authentication) {
		var userName = authentication.getName();
		org.springframework.security.core.userdetails.User authenticateduser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		com.ewsd.model.User user = userService.getUserByName(authenticateduser.getUsername());
	
	      
	  //    List<PostDto> allIdea = postService.getAllIdeaDtoWithCommentAndLike(user.getId());
		model.addAttribute("user", user);
		model.addAttribute("username", userName);
		
		//model.addAttribute("idea_list", allIdea);
		model.addAttribute("post_list", postService.getAll());
		model.addAttribute("message", "Showing All Ideas");
		return "post/view_all_ideas";
	}
	  public String convertTimestampToString(LocalDateTime timestamp, String format) {
	        Date date = new Date();
	        date.setTime(Timestamp.valueOf(LocalDateTime.now()).getTime());
	        String formattedDate = new SimpleDateFormat(format).format(date);
	        return formattedDate;
	    }

	

}
