package com.karpovskiy.newssite.Controller;

import com.karpovskiy.newssite.Entity.ArticleEntity;
import com.karpovskiy.newssite.Entity.UserEntity;
import com.karpovskiy.newssite.Security.Role;
import com.karpovskiy.newssite.Service.ArticleService;
import com.karpovskiy.newssite.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class NewsController {
    public final ArticleService articleService;
    public final UserService userService;

    public NewsController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping("/newArticle")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String getCreatingPage(@ModelAttribute("article") ArticleEntity articleEntity){
        return "newArticle";
    }

    @PostMapping("/newArticle")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public String createArticle(
            @AuthenticationPrincipal UserDetails author,
            @ModelAttribute("article") @Valid ArticleEntity articleEntity,
            BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "newArticle";
        }
        articleService.createArticle(articleEntity, userService.findUserByUsername(author.getUsername()));
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getOneArticle(@PathVariable("id") String id, Model model,
                                @AuthenticationPrincipal UserDetails currentUser){
        ArticleEntity article = articleService.getOneArticleById(id);
            model.addAttribute("article", article);
        if (currentUser != null){
            model.addAttribute("authorized", "true");
            if (article.getAuthorName().equals(currentUser.getUsername()) || userService.findUserByUsername(currentUser.getUsername()).getRole() == Role.ADMIN){
                model.addAttribute("changeable", "true");
            }
        }

        return "article";
    }

    @GetMapping("/{id}/like")
    public String likeArticle(@PathVariable("id") String id,
                              @AuthenticationPrincipal UserDetails user){
        UserEntity currentUser = userService.findUserByUsername(user.getUsername());
        ArticleEntity currentArticle = articleService.getOneArticleById(id);
        if (!currentUser.getLikedArticles().contains(currentArticle)){
            currentUser.addLikedArticle(currentArticle);
            currentArticle.addUserWhoLiked(currentUser);
            currentArticle.addLike();
            articleService.patchArticle(id, currentArticle);
            userService.patchUser(currentUser);
        } else if (currentUser.getLikedArticles().contains(currentArticle)){
            currentUser.removeLikedArticle(currentArticle);
            currentArticle.removeUserWhoLiked(currentUser);
            currentArticle.removeLike();
            articleService.patchArticle(id, currentArticle);
            userService.patchUser(currentUser);
        }
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable("id") String id){
        articleService.deleteArticleById(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String showPatchingPage(@PathVariable("id") String id,
                                   Model model){
        model.addAttribute("article", articleService.getOneArticleById(id));
        return "editArticle";
    }

    @PatchMapping("/{id}")
    public String patchArticle(@PathVariable("id") String id,
                               @ModelAttribute("article") @Valid ArticleEntity articleEntity,
                               BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "editArticle";
        }
        articleService.patchArticle(id, articleEntity);
        return "redirect:/{id}";
    }



    /*@GetMapping("/newuser")
    String nu(Model model){
        UserEntity user = new UserEntity();
        user.setId(IDGenerator.generateID());
        user.setUsername("Nik");
        user.setPassword("123");
        user.setActive(true);
        user.setRole(Role.ADMIN);
        userService.saveUserToDb(user); //need to import userRepo

        model.addAttribute("news", articleService.findAllArticlesByIdDesc());

        return "redirect:/news";
    }*/
}
