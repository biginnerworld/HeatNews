package com.karpovskiy.newssite.Controller;

import com.karpovskiy.newssite.Service.ArticleService;
import com.karpovskiy.newssite.Service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    public final UserService userService;
    public final ArticleService articleService;

    public HomeController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @GetMapping
    public String getHomePage(@AuthenticationPrincipal UserDetails currentUser,
                              Model model){
        model.addAttribute("news", articleService.findAllArticlesByIdDesc());
        if (currentUser != null){
            model.addAttribute("authorized", "true");
        }
        return "homePage";
    }
}
