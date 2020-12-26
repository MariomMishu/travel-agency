package com.ewsd.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ewsd.model.User;
import com.ewsd.repositories.UserRepository;
import com.ewsd.service.UserService;
import com.ewsd.util.Constants;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	private final PasswordEncoder passwordEncoder;

	public UserController(PasswordEncoder passwordEncoder) {
	this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/profile")
	public String edit_user(Model model, Authentication auth) {
		String absoluteFilePath = Constants.UPLOADED_FOLDER;
		var username = auth.getName();
		com.ewsd.model.User user = userService.getUserByName(username);
		model.addAttribute("image_path", absoluteFilePath);
		model.addAttribute("user", user);
		model.addAttribute("username", username);
		return "profile/show-profile";
	}

	@PostMapping("/profile/edit")
	public String add(@ModelAttribute(name = "user") User user, @RequestParam("file") MultipartFile file, Model model,
			Authentication authentication) {
		if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
			throw new RuntimeException("Please give User name");
		}

	//	var userName = authentication.getName();
		org.springframework.security.core.userdetails.User authenticateduser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
	//	var username = authenticateduser.getUsername();
		LocalDateTime update_date = LocalDateTime.now();

		com.ewsd.model.User u = userService.getUserByName(authenticateduser.getUsername());
		//var data = userService.getUserByUserName(userName).get();
	//	var UserId = data.getId();
		try {
			user.setId(u.getId());
			//user.setEmail(u.getEmail());
			user.setPassword(u.getPassword());
			user.setRole(u.getRole());
			//user.setDept(u.getDept());
			user.setEntryDate(u.getEntryDate());
			user.setActiveStatus(true);
			user.setIsExpired(true);
			user.setIsLocked(true);
			user.setUpdateDate(update_date);
			if (file.isEmpty()) {
				user.setProfilePic(u.getProfilePic());
			} else {
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
				var millis = System.currentTimeMillis();
				String url = dir.getAbsolutePath() + "//" + millis + "." + extension;
				File serverFile = new File(url);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				user.setProfilePic("/uploaded_img/" + millis + "." + extension);
			}
			user.setUsername(u.getUsername());

			userService.edit(user);

			model.addAttribute("message", "Profile Edited successfully");
			return "redirect:/profile";
			//return "profile/show-profile";
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

	}
	
	@GetMapping("/update-password")
	public String updatePassword_GET(Model model,Authentication auth) {
		var user_name = auth.getName();
		com.ewsd.model.User user = userService.getUserByName(user_name);
		long currentMinutes = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
		/*
		 * long minutesWhenRequested = TimeUnit.MILLISECONDS.toMinutes(timestamp);
		 * System.out.println(currentMinutes - minutesWhenRequested); if (currentMinutes
		 * - minutesWhenRequested > 2) { model.addAttribute("ok", "false"); return
		 * "/auth/reset_password"; }
		 */
//	model.addAttribute("id", username);
		model.addAttribute("user", user);
		model.addAttribute("username", user_name);
		model.addAttribute("ok", "");
		return "/profile/update_password";
	}

	@PostMapping("/update-password")
	public String updatePassword_POST(Model model,Authentication auth, @RequestParam("id") String username,
			@RequestParam("password") String password, @RequestParam("re_password") String rePassword) {
		var user_name = auth.getName();
		com.ewsd.model.User userEntity = userService.getUserByName(user_name);
	//	User userEntity = userService.getUserByName(username);
		if (password.equals(rePassword)) {
			userEntity.setPassword(passwordEncoder.encode(password));
			System.out.println(password);
			userService.edit(userEntity);
			model.addAttribute("ok", "true");
		} else {
			model.addAttribute("ok", "false");
		}

		return "/profile/update_password";
	}
}
