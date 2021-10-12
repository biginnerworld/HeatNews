package com.karpovskiy.newssite.Controller;

import com.karpovskiy.newssite.Entity.UserEntity;
import com.karpovskiy.newssite.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String getLoginView(){
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") UserEntity userEntity){
        return "registration";
    }

    @PostMapping("/registration")
    public String createNewUser(@ModelAttribute("user") @Valid UserEntity userEntity,
                                BindingResult bindingResult,
                                Model model){
        if (bindingResult.hasErrors()){
            return "registration";
        }
        if (userService.findUserByUsername(userEntity.getUsername()) != null){
            model.addAttribute("message", "This username is already taken");
            return "registration";
        }

        userService.saveUserToDb(userEntity);
        return "redirect:/";
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String getListOfUsersView(Model model){
        model.addAttribute("users", userService.getListOfUsers());
        return "userList";
    }

    @GetMapping("/list/{id}")
    public String getUserPage(@PathVariable("id") String id,
                              Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "userpage";
    }
}
