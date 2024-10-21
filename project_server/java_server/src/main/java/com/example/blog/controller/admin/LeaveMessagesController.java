package com.example.blog.controller.admin;

import com.example.blog.Model.LeaveMessages;
import com.example.blog.common.Result;
import com.example.blog.dto.IdReq;
import com.example.blog.dto.PageReq;
import com.example.blog.mapper.LeaveMessagesMapper;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/leaveMessages")
public class LeaveMessagesController {

    @Autowired
    private LeaveMessagesMapper leaveMessagesMapper;

    @PostMapping("list")
    public Result list(@Validated @RequestBody PageReq req, BindingResult err) {
        Result result = new Result();
        try {
            if (err.hasErrors()) {
                throw new Exception(err.getAllErrors().get(0).getDefaultMessage());
            }
            Map<String, Object> map = new HashMap<>();
            map.put("list", leaveMessagesMapper.GetList((req.getPage() - 1) * req.getPageSize(), req.getPageSize()));
            map.put("total", leaveMessagesMapper.GetListCount());
            result.setData(map);
        } catch (Exception e) {
            result.setCode(2500);
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
            leaveMessagesMapper.deleteById(req.getId());
        } catch (Exception e) {
            result.setCode(2500);
            result.setMsg(e.getMessage());
        }
        return result;

    }

    @RequestMapping("add")
    public Result add(HttpServletRequest request, String name, String content) throws Exception {
        Result result = new Result();
        try {
            Searcher searcher = Searcher
                    .newWithFileOnly("src/main/java/com/example/blog/common/ip2region.xdb");
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
        } catch (Exception e) {
            result.setCode(2500);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
