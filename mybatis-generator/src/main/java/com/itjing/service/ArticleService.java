package com.itjing.service;

import com.itjing.base.BaseServiceImpl;
import com.itjing.base.annotation.BaseService;
import com.itjing.entity.Article;
import com.itjing.entity.ArticleExample;
import com.itjing.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lijing
 * @date 2021年11月13日 9:26
 * @description
 */
@BaseService
@Service
public class ArticleService extends BaseServiceImpl<ArticleMapper, Article, ArticleExample> {

    @Autowired
    private ArticleMapper mapper;

    public List<Article> selectAll(Article article){
        return mapper.selectAll(article);
    }
}
