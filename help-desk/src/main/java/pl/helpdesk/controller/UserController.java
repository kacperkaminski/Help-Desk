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

import pl.helpdesk.model.User;
import pl.helpdesk.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/list")
	public String listUsers(Model theModel) {
		List<User> users = userService.getAllUsers();
		theModel.addAttribute("users", users);
		return "list-users";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		User theUser = new User();
		theModel.addAttribute("user", theUser);
		return "user-form";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User theUser) {
		userService.saveUser(theUser);
		return "redirect:/user/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int Id, Model theModel) {
		User theUser = userService.getUser(Id);
		theModel.addAttribute("user", theUser);
		return "user-form";
	}

	@GetMapping("/delete")
	public String deleteUser(@RequestParam("userId") int Id) {
		userService.deleteUser(Id);
		return "redirect:/user/list";
	}

	@PostMapping("/search")
	public String searchUsers(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		List<User> users = userService.searchUsers(theSearchName);
		theModel.addAttribute("users", users);
		return "list-users";
	}
}