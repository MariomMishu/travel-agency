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
import org.springframework.web.bind.annotation.RequestParam;

import com.ewsd.service.UserService;
import com.ewsd.util.Util;

@Controller
public class QAManagerController {

	/*
	 * @Autowired private TagService tagService;
	 * 
	 * @Autowired DepartmentService departmentService;
	 * 
	 * @Autowired UserService userService;
	 * 
	 * @GetMapping("/category/add") public String add_GET(Model model,
	 * Authentication authentication) { var userName = authentication.getName();
	 * org.springframework.security.core.userdetails.User authenticateduser =
	 * (org.springframework.security.core.userdetails.User) SecurityContextHolder
	 * .getContext().getAuthentication().getPrincipal(); com.ewsd.model.User user =
	 * userService.getUserByName(authenticateduser.getUsername());
	 * model.addAttribute("user", user); model.addAttribute("username", userName);
	 * model.addAttribute("catRm", new CategoryRm());
	 * model.addAttribute("dept_list", departmentService.getAll()); return
	 * "category/add"; }
	 * 
	 * @PostMapping("/category/add") public String add(Model
	 * model, @ModelAttribute("catRm") CategoryRm catRm, Authentication
	 * authentication) { LocalDateTime entry_date = LocalDateTime.now();
	 * org.springframework.security.core.userdetails.User authenticateduser =
	 * (org.springframework.security.core.userdetails.User)
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * com.ewsd.model.User user =
	 * userService.getUserByName(authenticateduser.getUsername());
	 * 
	 * System.out.println("Test"); System.out.println(catRm.getDeptId()); var deptId
	 * = departmentService.getById(catRm.getDeptId()); System.out.println(deptId);
	 * CategoryDto catDto = new CategoryDto(); Category catEntity = new Category();
	 * catDto.setOpeningDate(Util.getFormattedDate(catRm.getOpeningDate(),
	 * Util.DOB_DATE_FORMAT));
	 * catDto.setClosingDate(Util.getFormattedDate(catRm.getClosingDate(),
	 * Util.DOB_DATE_FORMAT));
	 * catDto.setFinalClosingDate(Util.getFormattedDate(catRm.getFinalClosingDate(),
	 * Util.DOB_DATE_FORMAT)); catDto.setName(catRm.getName());
	 * catDto.setDept(deptId); BeanUtils.copyProperties(catDto,catEntity);
	 * catEntity.setEntryDate(entry_date); catEntity.setIsDelete(true);
	 * catEntity.setEntryBy(user); tagService.add(catEntity);
	 * model.addAttribute("message", "New Category Added Successfully"); return
	 * "redirect:/category/show-all"; }
	 * 
	 * @GetMapping("/category/show-all") public String show_all(Model model,
	 * Authentication authentication) { var userName = authentication.getName();
	 * org.springframework.security.core.userdetails.User authenticateduser =
	 * (org.springframework.security.core.userdetails.User) SecurityContextHolder
	 * .getContext().getAuthentication().getPrincipal(); com.ewsd.model.User user =
	 * userService.getUserByName(authenticateduser.getUsername());
	 * model.addAttribute("user", user); model.addAttribute("username", userName);
	 * // model.addAttribute("deptName",); model.addAttribute("cat", new
	 * CategoryRm()); model.addAttribute("cat_list", tagService.getAll());
	 * model.addAttribute("message", "Showing All Category"); return
	 * "category/show-all"; }
	 * 
	 * @GetMapping("/category/edit") public String edit_GET(Model
	 * model, @RequestParam("id") long id, Authentication authentication) { var
	 * userName = authentication.getName();
	 * org.springframework.security.core.userdetails.User authenticateduser =
	 * (org.springframework.security.core.userdetails.User) SecurityContextHolder
	 * .getContext().getAuthentication().getPrincipal(); com.ewsd.model.User user =
	 * userService.getUserByName(authenticateduser.getUsername());
	 * model.addAttribute("user", user); model.addAttribute("username", userName);
	 * 
	 * var catDto = tagService.getById(id); var catRm = new CategoryRm();
	 * BeanUtils.copyProperties(catDto, catRm);
	 * catRm.setDeptId(catDto.getDept().getId());
	 * catRm.setDeptName(catDto.getDept().getDeptName());
	 * catRm.setName(catDto.getName());
	 * catRm.setOpeningDate(Util.getStringDate(catDto.getOpeningDate(),
	 * Util.DOB_DATE_FORMAT));
	 * catRm.setClosingDate(Util.getStringDate(catDto.getClosingDate(),
	 * Util.DOB_DATE_FORMAT));
	 * catRm.setFinalClosingDate(Util.getStringDate(catDto.getFinalClosingDate(),
	 * Util.DOB_DATE_FORMAT)); model.addAttribute("catRm", catRm);
	 * model.addAttribute("dept_list", departmentService.getAll()); return
	 * "category/edit"; }
	 * 
	 * @PostMapping("/category/edit") public String edit(Model
	 * model, @ModelAttribute("catRm") CategoryRm catRm, Authentication
	 * authentication) { LocalDateTime update_date = LocalDateTime.now();
	 * org.springframework.security.core.userdetails.User authenticateduser =
	 * (org.springframework.security.core.userdetails.User)
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * com.ewsd.model.User user =
	 * userService.getUserByName(authenticateduser.getUsername());
	 * 
	 * System.out.println(catRm.toString()); var deptId =
	 * departmentService.getById(catRm.getDeptId()); System.out.println(deptId);
	 * //CategoryDto catDto = new CategoryDto(); Category catEntity = new
	 * Category(); var catDto = tagService.getById(catRm.getId());
	 * catDto.setOpeningDate(Util.getFormattedDate(catRm.getOpeningDate(),
	 * Util.DOB_DATE_FORMAT));
	 * catDto.setClosingDate(Util.getFormattedDate(catRm.getClosingDate(),
	 * Util.DOB_DATE_FORMAT));
	 * catDto.setFinalClosingDate(Util.getFormattedDate(catRm.getFinalClosingDate(),
	 * Util.DOB_DATE_FORMAT)); catDto.setName(catRm.getName());
	 * catDto.setDept(deptId); BeanUtils.copyProperties(catDto,catEntity);
	 * 
	 * catEntity.setEntryBy(catDto.getEntryBy());
	 * catEntity.setEntryDate(catDto.getEntryDate());
	 * catEntity.setUpdateDate(update_date); catEntity.setIsDelete(true);
	 * catEntity.setUpdateBy(user);; tagService.edit(catEntity);
	 * model.addAttribute("message", "Category Edited Successfully"); return
	 * "redirect:/category/show-all"; }
	 * 
	 * @GetMapping("/category/delete") public String soft_delete_GET(Model
	 * model, @RequestParam("id") long id) {
	 * tagService.delete(tagService.findById(id)); model.addAttribute("message",
	 * "Category Deleted successfully"); return "redirect:/category/show-all"; }
	 */
}
