package com.karpovskiy.newssite.Entity;

import com.karpovskiy.newssite.Security.Role;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users_db")
public class UserEntity{
    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "username")
    @NotEmpty(message = "Name should not be empty.")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty.")
    @Size(min = 6, max = 20, message = "Incorrect size of the password")
    private String password;

    @Column(name = "user_role")
    private Role role;

    @Column(name = "active")
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<ArticleEntity> articles;

    @ManyToMany(mappedBy = "usersWhoLiked")
    private Set<ArticleEntity> likedArticles = new HashSet<>();


    public UserEntity(){}

    //GETTERS AND SETTERS

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleEntity> articles) {
        this.articles = articles;
    }

    public Set<ArticleEntity> getLikedArticles() {
        return likedArticles;
    }

    public void setLikedArticles(Set<ArticleEntity> likedArticles) {
        this.likedArticles = likedArticles;
    }

    public void addLikedArticle(ArticleEntity likedArticle){
        this.likedArticles.add(likedArticle);
    }

    public void removeLikedArticle(ArticleEntity likedArticle){
        this.likedArticles.remove(likedArticle);
    }
}
