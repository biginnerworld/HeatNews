package com.karpovskiy.newssite.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "articles")
public class ArticleEntity {

    @Id
    @Column(name = "article_id")
    private String id;

    @NotEmpty(message = "Heading can't be empty")
    @Size(max = 100, message = "Heading is too long")
    @Column(name = "heading")
    private String heading;

    @NotEmpty(message = "Content can't be empty")
    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private int likes;

    @Column(name = "publish_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private UserEntity author;

    @ManyToMany
    @JoinTable(
            name = "article_like",
            joinColumns = @JoinColumn(name = "id_of_liked_user"/*, referencedColumnName = "user_id"*/),
            inverseJoinColumns = @JoinColumn(name = "liked_article_id"/*, referencedColumnName = "article_id"*/)
    )
    private Set<UserEntity> usersWhoLiked = new HashSet<>();

    public ArticleEntity(){}

    /*public ArticleEntity(String heading, String content, int likes, int dislikes, UserEntity author) {
        this.heading = heading;
        this.content = content;
        this.likes = likes;
        this.dislikes = dislikes;
        this.author = author;
    }*/

    //GETTERS AND SETTERS

    public String getAuthorName(){
        return author.getUsername();
    }

    public String getAuthorId(){
        return author.getId();
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public String getStringDate(){
        return getDate().toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void addLike(){
        this.likes++;
    }

    public void removeLike(){
        this.likes--;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public Set<UserEntity> getUsersWhoLiked() {
        return usersWhoLiked;
    }

    public void setUsersWhoLiked(Set<UserEntity> usersWhoLiked) {
        this.usersWhoLiked = usersWhoLiked;
    }

    public void addUserWhoLiked(UserEntity userWhoLiked){
        this.usersWhoLiked.add(userWhoLiked);
    }

    public void removeUserWhoLiked(UserEntity userWhoLiked){
        this.usersWhoLiked.remove(userWhoLiked);
    }
}
