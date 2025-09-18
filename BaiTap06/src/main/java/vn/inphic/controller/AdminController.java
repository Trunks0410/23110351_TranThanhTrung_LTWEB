package vn.inphic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.inphic.service.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userservice;
	@Autowired
	private VideoService videoservice;

	@GetMapping
	public String showAdminDashboard(@RequestParam(name = "page", required = false) String page, Model model) {
		if ("category".equals(page)) {
			model.addAttribute("categories", categoryService.findAll());
			return "admin/category/list";
		} else if ("user".equals(page)) {
			model.addAttribute("users", userservice.findAll());
			return "admin/user/list";
		} else if ("video".equals(page)) {
			model.addAttribute("videos", videoservice.findAll());
			return "admin/video/list";
		}

		return "admin";
	}
}
