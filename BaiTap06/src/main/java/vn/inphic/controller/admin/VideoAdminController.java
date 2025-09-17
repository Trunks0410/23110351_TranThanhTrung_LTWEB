package vn.inphic.controller.admin;

import vn.inphic.entity.Video;
import vn.inphic.model.VideoModel;
import vn.inphic.service.CategoryService;
import vn.inphic.service.UserService;
import vn.inphic.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin/video")
public class VideoAdminController {

	@Autowired
	private VideoService service;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@GetMapping("/list")
	public String list(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "") String keyword) {
		Pageable pageable = PageRequest.of(page, 5);
		Page<Video> videos;
		if (!keyword.isEmpty()) {
			videos = service.findByTitleContaining(keyword, pageable);
		} else {
			videos = service.findAll(pageable);
		}
		model.addAttribute("videos", videos.getContent());
		model.addAttribute("totalPages", videos.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", keyword);
		model.addAttribute("message", model.containsAttribute("message") ? model.getAttribute("message") : "");
		return "admin/video/list";
	}

	@GetMapping("/form")
	public ModelAndView form(@RequestParam(required = false) Long id) {
		ModelAndView mav = new ModelAndView("admin/video/form");
		VideoModel model = new VideoModel();
		if (id != null) {
			Optional<Video> opt = service.findById(id);
			if (opt.isPresent()) {
				Video video = opt.get();
				BeanUtils.copyProperties(video, model);
				model.setCategoryId(video.getCategory() != null ? video.getCategory().getId() : null);
				model.setUserId(video.getUser() != null ? video.getUser().getId() : null);
				model.setEdit(true);
			}
		}
		mav.addObject("videoModel", model);
		mav.addObject("categories", categoryService.findAll());
		mav.addObject("users", userService.findAll());
		return mav;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid @ModelAttribute("videoModel") VideoModel model, BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("admin/video/form");
			mav.addObject("categories", categoryService.findAll());
			mav.addObject("users", userService.findAll());
			return mav;
		}
		Video video = new Video();
		BeanUtils.copyProperties(model, video);
		if (model.getCategoryId() != null) {
			video.setCategory(categoryService.findById(model.getCategoryId()).orElse(null));
		}
		if (model.getUserId() != null) {
			video.setUser(userService.findById(model.getUserId()).orElse(null));
		}
		service.save(video);
		ModelAndView mav = new ModelAndView("redirect:/admin/video/list");
		mav.addObject("message", "Video saved successfully");
		return mav;
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		service.deleteById(id);
		return "redirect:/admin/video/list?message=Video+deleted+successfully";
	}
}
