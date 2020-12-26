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



@Controller
public class StudentController {
	/*
	 * @Autowired UserService userService;
	 * 
	 * @Autowired TagService tagService;
	 * 
	 * @Autowired private IdeaService ideaService;
	 * 
	 * @Autowired private AttachmentService attachmentService;
	 * 
	 * @GetMapping("/idea/add") public String postNewIdea_GET(Model model,
	 * Authentication authentication) {
	 * 
	 * var userName = authentication.getName();
	 * org.springframework.security.core.userdetails.User authenticateduser =
	 * (org.springframework.security.core.userdetails.User) SecurityContextHolder
	 * .getContext().getAuthentication().getPrincipal(); com.ewsd.model.User user =
	 * userService.getUserByName(authenticateduser.getUsername());
	 * model.addAttribute("user", user); model.addAttribute("username", userName);
	 * model.addAttribute("ideaRm", new IdeaRm()); model.addAttribute("cat_list",
	 * tagService.getAll()); return "/idea/add"; }
	 * 
	 * @PostMapping("/idea/add") public String postNewIdea_POST(Model
	 * model, @ModelAttribute("ideaRm") IdeaRm ideaRm, Authentication
	 * authentication,
	 * 
	 * @RequestParam("images[]") MultipartFile[] files) { LocalDateTime entry_date =
	 * LocalDateTime.now(); org.springframework.security.core.userdetails.User
	 * authenticateduser = (org.springframework.security.core.userdetails.User)
	 * SecurityContextHolder .getContext().getAuthentication().getPrincipal();
	 * com.ewsd.model.User user =
	 * userService.getUserByName(authenticateduser.getUsername());
	 * 
	 * var catId = tagService.getById(ideaRm.getCatId()); System.out.println(catId);
	 * IdeaDto ideaDto = new IdeaDto(); Idea ideaEntity = new Idea();
	 * ideaDto.setIdeaTitle(ideaRm.getIdeaTitle());
	 * ideaDto.setIdeaBody(ideaRm.getIdeaBody()); ideaDto.setCat(catId);
	 * 
	 * InetAddress IP = null; try { IP = Inet4Address.getLocalHost(); } catch
	 * (UnknownHostException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } Set<Attachment> attachments = new
	 * HashSet<Attachment>(); if (files[0].getOriginalFilename().contains(".")) {
	 * for (MultipartFile file : files) { if (file.getSize() > 25000000L) {
	 * model.addAttribute("ideaRm", new IdeaRm()); model.addAttribute("usr", user);
	 * model.addAttribute("cat_list", tagService.getAll()); model.addAttribute("ok",
	 * "false"); model.addAttribute("msg", "Sorry, The file size is too large.");
	 * return "/idea/add"; } System.out.println(file.getContentType()); if
	 * (!file.getContentType().contains("image") &&
	 * !file.getOriginalFilename().contains(".doc") &&
	 * !file.getOriginalFilename().contains(".docx") &&
	 * !file.getContentType().contains("video") &&
	 * !file.getOriginalFilename().contains(".pdf")) {
	 * System.out.println(file.getOriginalFilename()); model.addAttribute("ideaRm",
	 * new IdeaRm()); model.addAttribute("usr", user);
	 * model.addAttribute("cat_list", tagService.getAll()); model.addAttribute("ok",
	 * "false"); model.addAttribute("msg", "Sorry, Unknown file type found.");
	 * return "/idea/add"; } Attachment attachment = new Attachment(); Long
	 * attachmentId = System.currentTimeMillis(); // attachment.setId(attachmentId);
	 * attachment.setFileName("" + attachmentId);
	 * attachment.setFileTitle(ideaRm.getIdeaTitle()); attachment =
	 * attachmentService.save(attachment, file, user.getId());
	 * attachments.add(attachment); }
	 * 
	 * }
	 * 
	 * ideaDto.setAttachments(attachments); BeanUtils.copyProperties(ideaDto,
	 * ideaEntity); ideaEntity.setEntryDate(entry_date);
	 * ideaEntity.setIsDelete(true); ideaEntity.setAuthorEmail(user.getEmail());
	 * ideaEntity.setCountViews(0); ideaEntity.setUserId(user);
	 * ideaService.add(ideaEntity); return "redirect:/idea/show-all"; }
	 * 
	 * @GetMapping("/idea/show-all") public String show_all(Model model,
	 * Authentication authentication) { var userName = authentication.getName();
	 * org.springframework.security.core.userdetails.User authenticateduser =
	 * (org.springframework.security.core.userdetails.User) SecurityContextHolder
	 * .getContext().getAuthentication().getPrincipal(); com.ewsd.model.User user =
	 * userService.getUserByName(authenticateduser.getUsername());
	 * model.addAttribute("user", user); model.addAttribute("username", userName);
	 * model.addAttribute("idea", new IdeaRm()); model.addAttribute("idea_list",
	 * ideaService.getAll()); model.addAttribute("message", "Showing All Ideas");
	 * return "idea/show-all"; }
	 * 
	 * @GetMapping("/idea/view_all_ideas") public String view_all_ideas(Model model,
	 * Authentication authentication) { var userName = authentication.getName();
	 * org.springframework.security.core.userdetails.User authenticateduser =
	 * (org.springframework.security.core.userdetails.User) SecurityContextHolder
	 * .getContext().getAuthentication().getPrincipal(); com.ewsd.model.User user =
	 * userService.getUserByName(authenticateduser.getUsername()); Map<String,
	 * List<Timeline>> dates = new TreeMap<>(Comparator.reverseOrder());
	 * 
	 * String dateTimeString = ""; for (Idea ideaEntity : ideaService.getAll()) {
	 * dateTimeString = convertTimestampToString(ideaEntity.getEntryDate(),
	 * "d/MM/YYYY hh:mm:ss aaa"); String time = dateTimeString.split(" ")[1] + " " +
	 * dateTimeString.split(" ")[2];
	 * 
	 * List<Comment> comments = new ArrayList<>();
	 * 
	 * for (Comment comment : ideaEntity.getComments()) { // if
	 * (user.getRole().equals("ROLE_ADMIN")) { comments.add(comment); // break; // }
	 * 
	 * }
	 * 
	 * ideaEntity.setComments(comments);
	 * 
	 * 
	 * } List<IdeaDto> allIdea =
	 * ideaService.getAllIdeaDtoWithCommentAndLike(user.getId());
	 * model.addAttribute("user", user); model.addAttribute("username", userName);
	 * model.addAttribute("comment", new CommentRm());
	 * model.addAttribute("idea_list", allIdea); //model.addAttribute("idea_list2",
	 * ideaService.getAll()); model.addAttribute("message", "Showing All Ideas");
	 * return "idea/view_all_ideas"; } public String
	 * convertTimestampToString(LocalDateTime timestamp, String format) { Date date
	 * = new Date(); date.setTime(Timestamp.valueOf(LocalDateTime.now()).getTime());
	 * String formattedDate = new SimpleDateFormat(format).format(date); return
	 * formattedDate; }
	 * 
	 */

}
