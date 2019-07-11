package com.blog.service.impl;

import com.blog.exception.TipException;
import com.blog.mapper.ArticleMapper;
import com.blog.model.Article;
import com.blog.model.ArticleExample;
import com.blog.service.ArticleService;
import com.blog.utils.DateKit;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class ArticleServiceimpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageInfo<Article> listAllArticle() {
        log.debug("开始查找文章");
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("created desc");
        PageHelper.startPage(0, 20);
        List<Article> data = articleMapper.selectByExample(example);
        log.info("查找文章结束");
        return new PageInfo<>(data);
    }

    @Override
    public void publish(Article article) {
        if(null == article){
            throw new TipException("文章对象为空");
        }
        if(StringUtils.isBlank(article.getTitle())){
            throw new TipException("文章标题不能为空");
        }
        if(StringUtils.isBlank(article.getAuthor())){
            throw new TipException("文章作者不能为空");
        }
        if(StringUtils.isBlank(article.getTitle())){
            throw new TipException("文章内容不能为空");
        }
        log.info("insert a article： the article is :" + article.getAuthor());
        int time = DateKit.getCurrentUnixTime();
        article.setCreated(time);
        article.setModified(time);
        article.setContent(EmojiParser.parseToAliases(article.getContent()));
        articleMapper.insert(article);
        log.info("insert succeed!");
    }

    @Override
    public PageInfo<Article> getArticle(Integer p, Integer limit) {
        log.debug("开始查找文章");
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("created desc");
        PageHelper.startPage(p, limit);
        List<Article> data = articleMapper.selectByExample(example);
        log.info("查找文章结束");
        return new PageInfo<>(data);
    }

    @Override
    public PageInfo<Article> getArticles(String keyword, Integer page, Integer limit) {
        log.debug("开始查找文章");
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("created desc");
        example.or().andTitleLike("%" + keyword + "%");
        PageHelper.startPage(page, limit);
        List<Article> data = articleMapper.selectByExample(example);
        log.info("查找文章结束");
        return new PageInfo<>(data);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateByPrimaryKey(article);
    }

    @Override
    public void deleteArticle(Integer cid) {
        log.debug("删除网页");
        articleMapper.deleteByPrimaryKey(cid);
        log.debug("删除成功");
    }
}
