package com.lianjia.sh.kanban.controller;

import com.lianjia.sh.kanban.bean.MessageResult;
import com.lianjia.sh.kanban.login.LoginService;
import com.lianjia.sh.kanban.model.Dict;
import com.lianjia.sh.kanban.model.Member;
import com.lianjia.sh.kanban.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ouyang
 * @since 2016-07-07 11:44
 */
@RestController
public class DictController {

    @Autowired
    private DictService dictService;

    @Autowired
    private LoginService loginProcessor;

    @RequestMapping(value = "/v1/dicts", method = RequestMethod.GET)
    public MessageResult<List<Dict>> list(
            @RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false, value = "ename") String ename,
            @RequestParam(required = false, value = "parentCode") Long parentCode,
            @RequestParam(required = false, value = "code") Long code) {

        List<Dict> dicts = dictService.list(name, ename, parentCode, code);

        return MessageResult.success(dicts);

    }

    @RequestMapping(value = "/v1/dict", method = RequestMethod.POST)
    public MessageResult<Dict> add(
            HttpServletRequest request,
            @RequestParam(required = true, value = "name") String name,
            @RequestParam(required = true, value = "ename") String ename,
            @RequestParam(required = true, value = "desc") String desc,
            @RequestParam(required = true, value = "key") String key,
            @RequestParam(required = true, value = "value") String value,
            @RequestParam(required = true, value = "parentCode") Long parentCode,
            @RequestParam(required = true, value = "code") Long code) {

        Member member = loginProcessor.getMember(request);

        Dict dict = dictService.insert(name, ename, desc, key, value, parentCode, code, member.getId());

        return MessageResult.success(dict);

    }


    @RequestMapping(value = "/v1/dict/{id}", method = RequestMethod.POST)
    public MessageResult<Dict> add(
            HttpServletRequest request,
            @PathVariable Integer id,
            @RequestParam(required = true, value = "name") String name,
            @RequestParam(required = true, value = "ename") String ename,
            @RequestParam(required = true, value = "desc") String desc) {

        Member member = loginProcessor.getMember(request);

        Dict dict = dictService.update(id, name, ename, desc, member.getId());

        return MessageResult.success(dict);

    }


    @RequestMapping(value = "/v1/dict/{id}", method = RequestMethod.DELETE)
    public MessageResult<Integer> delete(HttpServletRequest request, @PathVariable(value = "id") Integer id) {

        Member member = loginProcessor.getMember(request);

        dictService.delete(id, member.getId());

        return MessageResult.success(id);
    }

    @RequestMapping(value = "/v1/dict/{id}", method = RequestMethod.GET)
    public MessageResult<Dict> select(@PathVariable(value = "id") Integer id) {
        Dict dict = dictService.select(id);
        return MessageResult.success(dict);
    }


}
