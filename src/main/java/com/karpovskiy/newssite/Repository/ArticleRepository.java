package com.karpovskiy.newssite.Repository;

import com.karpovskiy.newssite.Entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    ArticleEntity getNewsEntityById(String id);
    void deleteArticleEntityById(String id);
}
