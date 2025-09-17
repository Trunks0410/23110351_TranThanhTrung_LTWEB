package vn.inphic.controller;

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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/list")
    public String list(Model model) {
        List<Category> categories = service.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("message", "");  // Message placeholder
        return "list";
    }

    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView mav = new ModelAndView("form");
        CategoryModel model = new CategoryModel();
        model.setEdit(false);  // Insert mode
        mav.addObject("categoryModel", model);
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("categoryModel") CategoryModel categoryModel,
                             BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return new ModelAndView("form");
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryModel, category);
        service.save(category);
        modelMap.addAttribute("message", "Category saved successfully");
        return new ModelAndView("redirect:/category/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Optional<Category> optional = service.findById(id);
        if (optional.isPresent()) {
            Category category = optional.get();
            CategoryModel model = new CategoryModel();
            BeanUtils.copyProperties(category, model);
            model.setEdit(true);  // Edit mode
            return new ModelAndView("form", "categoryModel", model);
        } else {
            return new ModelAndView("redirect:/category/list");
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        ModelAndView mav = new ModelAndView("redirect:/category/list");
        mav.addObject("message", "Category deleted successfully");
        return mav;
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String keyword,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "3") int size,
                         Model model) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Category> resultPage;
        if (!keyword.isEmpty()) {
            resultPage = service.findByNameContaining(keyword, pageable);
        } else {
            resultPage = service.findAll(pageable);
        }
        int totalPages = resultPage.getTotalPages();
        model.addAttribute("categories", resultPage.getContent());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "list"; 
    }
}
