package com.example.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.blog.Model.Articles;
import com.example.blog.common.Result;
import com.example.blog.dto.ArticleDetailResp;
import com.example.blog.dto.IdReq;
import com.example.blog.dto.PageReq;
import com.example.blog.dto.articles.AddReq;
import com.example.blog.dto.articles.ChangeStatusReq;
import com.example.blog.dto.articles.InfoResp;
import com.example.blog.dto.articles.ListResp;
import com.example.blog.dto.articles.UpdateReq;
import com.example.blog.mapper.ArticleTagRelaMapper;
import com.example.blog.mapper.ArticlesMapper;
import com.example.blog.mapper.TagsMapper;
import com.example.blog.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/articles")
public class ArticlesController {
    @Autowired
    private ArticlesService articleService;
    @Autowired
    private ArticlesMapper articlesMapper;
    @Autowired
    private TagsMapper tagsMapper;
    @Autowired
    private ArticleTagRelaMapper articleTagRelaMapper;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @PostMapping("add")
    public Result Add(@Validated @RequestBody AddReq req, BindingResult err) {
        Result result = new Result();
        // 创建事务定义
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // 设置事务属性，例如传播行为和隔离级别
        def.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
        // 开始事务
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            Articles articles = new Articles();
            articles.setContent(req.getContent());
            articles.setTitle(req.getTitle());
            articles.setStatus(req.getStatus());
            articles.setCreateTime(new Date());
            int row = articlesMapper.insert(articles);
            if (row != 1) {
                throw new Exception("新增文章失败~");
            }
            if (req.getTagsId().length > 0) {
                // 更新标签文章数量
                tagsMapper.IncrementArticleSumByIdArr(req.getTagsId());
                // 新增文章标签关系记录
                articleTagRelaMapper.InsertBatch(req.getTagsId(), articles.getId());
            }

            // 提交事务
            transactionManager.commit(status);
        } catch (Exception e) {
            result.setCode(2400);
            result.setMsg(e.getMessage());
            // 回滚事务
            transactionManager.rollback(status);
        }
        return result;
    }

    @PostMapping("update")
    public Result Update(@Validated @RequestBody UpdateReq req, BindingResult err) {
        Result result = new Result();
        // 创建事务定义
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // 设置事务属性，例如传播行为和隔离级别
        def.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
        // 开始事务
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            Articles articles = new Articles();
            articles.setId(req.getId());
            articles.setContent(req.getContent());
            articles.setTitle(req.getTitle());
            articles.setStatus(req.getStatus());
            articles.setCreateTime(req.getCreateTime());
            articles.setUpdateTime(req.getUpdateTime());

            int row = articlesMapper.updateById(articles);
            if (row != 1) {
                throw new Exception("修改失败~");
            }
            // 先查旧的文章标签id，要 减掉article_sum
            Integer[] oldTagIdArr = tagsMapper.GetListByArticleId(req.getId());
            if (oldTagIdArr.length > 0) {
                UpdateWrapper updateWrapper = new UpdateWrapper<>();
                updateWrapper.in("id", oldTagIdArr);
                updateWrapper.setSql("article_sum = article_sum - 1");
                tagsMapper.update(null, updateWrapper);
            }

            // 删除文章标签关系
            articleTagRelaMapper.DeleteByArticleId(req.getId());
            if (req.getTagsId().length > 0) {
                // 更新标签文章数量
                tagsMapper.IncrementArticleSumByIdArr(req.getTagsId());
                // 新增文章标签关系记录
                articleTagRelaMapper.InsertBatch(req.getTagsId(), articles.getId());
            }

            // 提交事务
            transactionManager.commit(status);
        } catch (Exception e) {
            result.setCode(2400);
            result.setMsg(e.getMessage());
            // 回滚事务
            transactionManager.rollback(status);
        }
        return result;
    }

    @PostMapping("list")
    public Result List(@Validated @RequestBody PageReq req, BindingResult err) {
        Result result = new Result();
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            ListResp data = new ListResp();
            data.setList(articlesMapper.GetList((req.getPage() - 1) * req.getPageSize(), req.getPageSize()));
            data.setTotal(articlesMapper.GetListCount());
            result.setData(data);
        } catch (Exception e) {
            result.setCode(2400);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("info")
    public Result Info(@Validated @RequestBody IdReq req, BindingResult err) {
        Result result = new Result();
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            Articles row = articlesMapper.selectById(req.getId());
            if (row == null) {
                throw new Exception("操作失败~");
            }
            InfoResp data = new InfoResp();
            data.setId(row.getId());
            data.setStatus(row.getStatus());
            data.setContent(row.getContent());
            data.setCreateTime(row.getCreateTime());
            data.setUpdateTime(row.getUpdateTime());
            data.setTitle(row.getTitle());
            data.setTagsIdList(tagsMapper.GetListByArticleId(req.getId()));
            result.setData(data);
        } catch (Exception e) {
            result.setCode(2300);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("changeStatus")
    public Result ChangeStatus(@Validated @RequestBody ChangeStatusReq req, BindingResult err) {
        Result result = new Result();
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            UpdateWrapper updateWrapper = new UpdateWrapper<>();
            updateWrapper.in("id", req.getId());
            updateWrapper.set("status", req.getStatus());
            int row = articlesMapper.update(null, updateWrapper);
            if (row == 0) {
                throw new Exception("更新状态失败~~");
            }
        } catch (Exception e) {
            result.setCode(2300);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("delete")
    public Result Delete(@Validated @RequestBody IdReq req, BindingResult err) {
        Result result = new Result();
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            int row = articlesMapper.deleteById(req.getId());
            if (row == 0) {
                throw new Exception("操作失败~");
            }
        } catch (Exception e) {
            result.setCode(2300);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping("list2")
    public Result List2(String page, String tagId) {
        Result result = new Result();
        try {
            int pageNum = 0;
            Map<String, Integer> param = new HashMap<>();
            if (null != tagId && tagId != "") {
                int tag = Integer.parseUnsignedInt(tagId);
                param.put("tagId", tag);
            }
            if (null != page && page != "") {
                pageNum = Integer.parseUnsignedInt(page);
            }
            int offset = pageNum * 10;
            param.put("offset", offset);
            Map<String, Object> map = new HashMap<>();
            map.put("articleList", articleService.list(param));
            map.put("total", articleService.count(param));
            result.setData(map);
        } catch (NumberFormatException e) {
            result.setData("page或tagId格式错误");
            result.setCode(1);
        }

        return result;
    }

    @PostMapping("detail")
    public Result detail(String id) {
        Result result = new Result();
        try {
            int articleId = Integer.parseInt(id);
            ArticleDetailResp articleDetailResp = articleService.detail(articleId);
            if (null == articleDetailResp) {
                result.setMsg("文章不存在");
            } else {
                result.setData(articleDetailResp);
            }
        } catch (NumberFormatException e) {
            result.setMsg("id格式错误");
        }
        return result;
    }

}
