package vn.inphic.controller.admin;

import vn.inphic.entity.User;
import vn.inphic.model.UserModel;
import vn.inphic.service.UserService;
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
@RequestMapping("/admin/user")
public class UserAdminController {

    @Autowired
    private UserService service;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword) {
        Pageable pageable = PageRequest.of(page, 5); // 5 items per page
        Page<User> users;
        if (!keyword.isEmpty()) {
            users = service.findByUsernameContaining(keyword, pageable);
        } else {
            users = service.findAll(pageable);
        }
        model.addAttribute("users", users.getContent());
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("message", model.containsAttribute("message") ? model.getAttribute("message") : "");
        return "admin/user/list";
    }

    @GetMapping("/form")
    public ModelAndView form(@RequestParam(required = false) Long id) {
        ModelAndView mav = new ModelAndView("admin/user/form");
        UserModel model = new UserModel();
        if (id != null) {
            Optional<User> opt = service.findById(id);
            if (opt.isPresent()) {
                BeanUtils.copyProperties(opt.get(), model);
                model.setPassword(""); // Không hiển thị password
                model.setEdit(true);
            }
        }
        mav.addObject("userModel", model);
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("userModel") UserModel model, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/user/form");
        }
        User user = new User();
        BeanUtils.copyProperties(model, user);
        // Chỉ mã hóa password nếu được nhập (insert hoặc update với password mới)
        if (model.getPassword().isEmpty() && model.isEdit()) {
            Optional<User> existingUser = service.findById(model.getId());
            if (existingUser.isPresent()) {
                user.setPassword(existingUser.get().getPassword()); // Giữ password cũ
            }
        }
        service.save(user);
        ModelAndView mav = new ModelAndView("redirect:/admin/user/list");
        mav.addObject("message", "User saved successfully");
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/admin/user/list?message=User+deleted+successfully";
    }
}
