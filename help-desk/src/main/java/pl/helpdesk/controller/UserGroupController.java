package pl.helpdesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.helpdesk.model.UserGroup;
import pl.helpdesk.service.UserGroupService;


@Controller
@RequestMapping("/group")
public class UserGroupController {

	@Autowired
	private UserGroupService userGroupService;
	
	@GetMapping("/list")
	public String listUsers(Model theModel) {	
		List<UserGroup> userGroups = userGroupService.getAllGroups();
		theModel.addAttribute("userGroup", userGroups);
		return "list-groups";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {	
		UserGroup userGroup = new UserGroup();
		theModel.addAttribute("userGroup", userGroup);
		return "group-form";
	}
	
	@PostMapping("/saveUserGroup")
	public String saveUser(@ModelAttribute("userGroup") UserGroup userGroup) {
		userGroupService.saveUserGroup(userGroup);
		return"redirect:/group/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userGroupId") int id, Model theModel) {
		UserGroup userGroup = userGroupService.getUserGroup(id);
		theModel.addAttribute("userGroup", userGroup);
		return "group-form";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("userGroupId") int id) {
		userGroupService.deleteUserGroup(id);
		return"redirect:/group/list";
	}
}
