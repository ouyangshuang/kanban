package com.lianjia.sh.kanban.controller;

import com.lianjia.sh.kanban.bean.MessageCodeEnum;
import com.lianjia.sh.kanban.bean.MessageResult;
import com.lianjia.sh.kanban.framework.DateUtils;
import com.lianjia.sh.kanban.login.LoginService;
import com.lianjia.sh.kanban.model.Case;
import com.lianjia.sh.kanban.model.Member;
import com.lianjia.sh.kanban.service.CaseService;
import com.lianjia.sh.kanban.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

/**
 * @author ouyang
 * @since 2016-07-08 10:42
 */
@RestController
public class CaseController {

    @Autowired
    private LoginService loginProcessor;

    @Autowired
    private CaseService caseService;

    @Autowired
    private DictService dictService;

    /**
     * 添加需求
     * @param request
     * @param name 需求名称
     * @param chandaoId 禅道id
     * @param desc 描述
     * @param type 需求类型
     * @param level 需求优先级
     * @param transferExpectAt
     * @return
     */
    @RequestMapping(value = "/v1/case", method = RequestMethod.POST)
    public MessageResult<Case> add(
            HttpServletRequest request,
            @RequestParam(required = true, value = "name") String name,
            @RequestParam(required = true, value = "chandaoId") Integer chandaoId,
            @RequestParam(required = false, value = "desc", defaultValue = "") String desc,
            @RequestParam(required = true, value = "type") Integer type,
            @RequestParam(required = true, value = "level") Integer level,
            @RequestParam(required = true, value = "transferExpectAt") String transferExpectAt ) {

        Member member = loginProcessor.getMember(request);

        Date transferExpectTime = null;

        try {
            transferExpectTime = DateUtils.getDateByString(transferExpectAt, DateUtils.DEFAULT_DATETIME_FORMAT);
        } catch (ParseException e) {
            return MessageResult.fail(null, MessageCodeEnum.日期格式错误, String.format("格式默认为%s", DateUtils.DEFAULT_DATETIME_FORMAT));
        }

        Case myCase = caseService.insert(name,chandaoId,desc,type,level,transferExpectTime,member.getId());

        return MessageResult.success(myCase);
    }

}
