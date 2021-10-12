package com.karpovskiy.newssite.Service;

import com.karpovskiy.newssite.Entity.ArticleEntity;
import com.karpovskiy.newssite.Entity.UserEntity;
import com.karpovskiy.newssite.Generator.IDGenerator;
import com.karpovskiy.newssite.Repository.ArticleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<ArticleEntity> findAllArticlesByIdDesc(){
        return articleRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public void createArticle(ArticleEntity articleEntity, UserEntity author){
        articleEntity.setId(IDGenerator.generateID());
        articleEntity.setDate(new Date());
        articleEntity.setLikes(0);
        articleEntity.setAuthor(author);
        articleRepository.save(articleEntity);
    }

    public ArticleEntity getOneArticleById(String id){
        return articleRepository.getNewsEntityById(id);
    }

    public void deleteArticleById(String id){
        articleRepository.deleteArticleEntityById(id);
    }

    public void patchArticle(String id, ArticleEntity editedArticleEntity){
        ArticleEntity articleEntity = articleRepository.getNewsEntityById(id);
        articleEntity.setContent(editedArticleEntity.getContent());
        articleEntity.setHeading(editedArticleEntity.getHeading());
        articleRepository.save(articleEntity);
    }
}
