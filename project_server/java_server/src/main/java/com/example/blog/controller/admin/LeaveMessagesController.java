package com.example.blog.controller.admin;

import com.example.blog.Model.LeaveMessages;
import com.example.blog.common.Result;
import com.example.blog.service.LeaveMessagesService;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    private LeaveMessagesService leaveMessageService;

    @PostMapping("list")
    public Result list(String page) {
        Result result = new Result();
        int pageNum = 0;
        try {
            pageNum = Integer.parseUnsignedInt(page);
        } catch (NumberFormatException e) {
        }
        int offset = pageNum * 10;
        Map<String, Object> map = new HashMap<>();
        map.put("list", leaveMessageService.list(offset));
        result.setData(map);
        return result;
    }

    @RequestMapping("add")
    public Result add(HttpServletRequest request, String name, String content) throws Exception {
        Searcher searcher = Searcher
                .newWithFileOnly("C:\\Hello\\blog\\src\\main\\java\\com\\example\\blog\\common\\ip2region.xdb");
        String ip = request.getRemoteHost();
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        Result result = new Result();
        LeaveMessages leaveMessage = new LeaveMessages();
        leaveMessage.setName(name);
        leaveMessage.setContent(content);
        leaveMessage.setCreateTime(new Date());
        leaveMessage.setIp(ip);
        leaveMessage.setIpAddr(searcher.search(ip));
        int code = leaveMessageService.add(leaveMessage);
        if (code > 0) {
            result.setData("留言成功");
        } else {
            result.setData("留言失败");
            result.setCode(1);
        }
        return result;
    }
}
