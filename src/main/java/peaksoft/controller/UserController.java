package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.User;
import peaksoft.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/save")
    public String save(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "form_login";
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:user/find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_users", userService.findAll());
        return "all_users";
    }

    @GetMapping("/find_by_id")
    public String findById(@RequestParam("test") int id, Model model) {
        model.addAttribute("only_one_user", userService.findById(id));
        return "only_one_user";
    }

    @GetMapping("/update/{user_id}")
    public String update(Model model, @PathVariable("user_id") int id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update_user";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute User user, @PathVariable int id) {
        userService.update(id, user);
        return "redirect:/user/find_all";
    }

}
