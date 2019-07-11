package com.blog.service;

import com.blog.model.Article;
import com.github.pagehelper.PageInfo;

/**
 * 文章Service
 */
public interface ArticleService {

    //列出所有文章
    PageInfo<Article> listAllArticle();

    /**
     * 发布文章
     * @param article
     */
    void publish(Article article);

    /**
     * 查询文章返回多条数据
     * @param p 当前页
     * @param limit 每页条数
     * @return Article
     */
    PageInfo<Article> getArticle(Integer p, Integer limit);

    /**
     * 搜索、分页
     * @param keyword
     * @param page
     * @param limit
     * @return Article
     */
    PageInfo<Article> getArticles(String keyword, Integer page, Integer limit);

    /**
     * 编辑文章
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 删除文章
     * @param cid
     */
    void deleteArticle(Integer cid);

}
