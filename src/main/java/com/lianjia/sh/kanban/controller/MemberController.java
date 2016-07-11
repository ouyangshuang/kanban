package com.lianjia.sh.kanban.controller;

import com.lianjia.sh.kanban.bean.MessageResult;
import com.lianjia.sh.kanban.model.Member;
import com.lianjia.sh.kanban.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ouyang
 * @since 2016-07-07 15:35
 */
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/v1/members", method = RequestMethod.GET)
    public MessageResult<List<Member>> list(@RequestParam(required = false, value = "teamId") Integer teamId){
        List<Member> list = memberService.list(teamId);
        return MessageResult.success(list);
    }

}
