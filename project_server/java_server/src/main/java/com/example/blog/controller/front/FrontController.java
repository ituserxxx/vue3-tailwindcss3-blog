package com.example.blog.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.Model.Articles;
import com.example.blog.Model.LeaveMessages;
import com.example.blog.common.Result;
import com.example.blog.dto.front.ArticleDetailResp;
import com.example.blog.dto.front.ArticlesListResp;
import com.example.blog.mapper.ArticlesMapper;
import com.example.blog.mapper.LeaveMessagesMapper;
import com.example.blog.mapper.TagsMapper;
import java.net.URL;

@RestController
@RequestMapping("blog/")
public class FrontController {

    @Autowired
    private ArticlesMapper articlesMapper;

    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private LeaveMessagesMapper leaveMessagesMapper;

    @PostMapping("articles/list")
    public Result ArticleList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "tagId", defaultValue = "0") Integer tagId) {
        Result result = new Result();
        try {
            ArticlesListResp data = new ArticlesListResp();
            data.setList(articlesMapper.GetArticlesListByTagId((page - 1) * 8, 8, tagId));
            data.setTotal(articlesMapper.GetArticlesListByTagIdCount(tagId));
            result.setData(data);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setCode(9001);
        }
        return result;
    }

    @PostMapping("articles/detail")
    public Result ArticleDetail(@RequestParam(value = "id", required = true) Integer id) {
        Result result = new Result();
        try {
            ArticleDetailResp data = new ArticleDetailResp();
            Articles articles = articlesMapper.selectById(id);
            data.setContent(articles.getContent());
            data.setId(articles.getId());
            data.setCreateTime(articles.getCreateTime());
            data.setStatus(articles.getStatus());
            data.setTitle(articles.getTitle());
            data.setUpdateTime(articles.getUpdateTime());
            data.setTagsIdList(tagsMapper.GetAllByArticleId(id));
            result.setData(data);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setCode(9001);
        }
        return result;
    }

    @PostMapping("tags/list")
    public Result TagsList() {
        Result result = new Result();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("list", tagsMapper.GetAll());
            result.setData(map);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setCode(9001);
        }
        return result;
    }

    @PostMapping("leaveMessages/list")
    public Result LeaveMessagesList(
            @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Result result = new Result();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("list", leaveMessagesMapper.GetList((page - 1) * 10, 10));
            result.setData(map);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setCode(9001);
        }
        return result;
    }

    @RequestMapping("leaveMessages/add")
    public Result add(HttpServletRequest request, String name, String content) throws Exception {
        Result result = new Result();
        try {
            URL resource = getClass().getClassLoader().getResource("ip2region.xdb");
            String path = resource.getPath();
            Searcher searcher = Searcher.newWithFileOnly(path);
            String ip = request.getRemoteHost();
            if (ip.equals("0:0:0:0:0:0:0:1")) {
                ip = "127.0.0.1";
            }
            LeaveMessages leaveMessage = new LeaveMessages();
            leaveMessage.setName(name);
            leaveMessage.setContent(content);
            leaveMessage.setCreateTime(new Date());
            leaveMessage.setIp(ip);
            leaveMessage.setIpAddr(searcher.search(ip));
            int row = leaveMessagesMapper.insert(leaveMessage);
            if (row == 0) {
                throw new Exception("操作失败");
            }
            leaveMessage.setIp("");
            leaveMessage.setIpAddr("");
            result.setData(leaveMessage);
        } catch (Exception e) {
            result.setCode(2500);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
