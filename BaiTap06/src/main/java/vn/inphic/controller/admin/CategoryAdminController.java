package vn.inphic.controller.admin;

import vn.inphic.entity.Category;
import vn.inphic.model.CategoryModel;
import vn.inphic.service.CategoryService;
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
@RequestMapping("/admin/category")
public class CategoryAdminController {
    @Autowired
    private CategoryService service;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword) {
        Pageable pageable = PageRequest.of(page, 5); // 5 items/page
        Page<Category> categories;
        if (!keyword.isEmpty()) {
            categories = service.findByNameContaining(keyword, pageable);
        } else {
            categories = service.findAll(pageable);
        }
        model.addAttribute("categories", categories.getContent());
        model.addAttribute("totalPages", categories.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "admin/category/list";
    }

    @GetMapping("/form")
    public ModelAndView form(@RequestParam(required = false) Long id) {
        ModelAndView mav = new ModelAndView("admin/category/form");
        CategoryModel model = new CategoryModel();
        if (id != null) {
            Optional<Category> opt = service.findById(id);
            if (opt.isPresent()) {
                BeanUtils.copyProperties(opt.get(), model);
                model.setEdit(true);
            }
        }
        mav.addObject("categoryModel", model);
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("categoryModel") CategoryModel model, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/category/form");
        }
        Category category = new Category();
        BeanUtils.copyProperties(model, category);
        service.save(category);
        return new ModelAndView("redirect:/admin/category/list");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/admin/category/list";
    }
}
