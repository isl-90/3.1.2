package kata.project1.project1.controller;

import jakarta.validation.Valid;
import kata.project1.project1.model.User;
import kata.project1.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/people")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("users", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("users") User user) {
        return "new";
    }

    @PostMapping() // "/new"
    public String create(@ModelAttribute("users") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";
        userService.addUser(user);
        return "redirect:/people";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("users", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("users") @Valid User user,
                         BindingResult bindingResult, @RequestParam("id") int id) {
        if (bindingResult.hasErrors())
            return "edit";
        userService.updateUser(id, user);
        return "redirect:/people";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.removeUser(id);
        return "redirect:/people";
    }
}
