package com.lianjia.sh.kanban.controller;

import com.lianjia.sh.kanban.bean.MessageResult;
import com.lianjia.sh.kanban.login.LoginService;
import com.lianjia.sh.kanban.model.Member;
import com.lianjia.sh.kanban.model.Team;
import com.lianjia.sh.kanban.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ouyang
 * @since 2016-07-06 16:13
 */
@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private LoginService loginService;


    /**
     * 团队列表
     *
     * @return
     * @author ouyang
     * @version v1
     * @summary 团队列表
     * @since 2016-07-06 17:18:01
     */
    @RequestMapping(value = "/v1/teams", method = RequestMethod.GET)
    public MessageResult<List<Team>> list(@RequestParam(required = false, value = "name") String name) {
        List<Team> list = teamService.list(name);
        return MessageResult.success(list);
    }

    /**
     * 新增team
     *
     * @param name
     * @param title
     * @return
     * @author ouyang
     * @version v1
     * @summary 新增team
     * @since 2016-07-06 17:05:58
     */
    @RequestMapping(value = "/v1/team", method = RequestMethod.POST)
    public MessageResult<Team> add(
            HttpServletRequest request,
            @RequestParam(required = true, value = "name") String name,
            @RequestParam(required = true, value = "title") String title) {
        Member member = loginService.getMember(request);
        Team team = teamService.insert(name, title, member.getId());
        return MessageResult.success(team);
    }

    /**
     * 删除团队
     *
     * @param id
     * @return
     * @author ouyang
     * @version v1
     * @summary 删除团队
     * @since 2016-07-06 17:07:11
     */
    @RequestMapping(value = "/v1/team/{id}", method = RequestMethod.DELETE)
    public MessageResult<Integer> delete(HttpServletRequest request, @PathVariable(value = "id") Integer id) {
        Member member = loginService.getMember(request);
        teamService.delete(id, member.getId());
        return MessageResult.success(id);
    }


    /**
     * 修改团队信息
     *
     * @param id    id
     * @param name  团队名称
     * @param title 团队口号
     * @return
     * @author ouyang
     * @version v1
     * @summary 修改团队信息
     * @since 2016-07-06 17:07:41
     */
    @RequestMapping(value = "/v1/team/{id}", method = RequestMethod.POST)
    public MessageResult<Team> update(
            HttpServletRequest request,
            @PathVariable Integer id,
            @RequestParam(required = true, value = "name") String name,
            @RequestParam(required = true, value = "title") String title) {
        Member member = loginService.getMember(request);
        Team team = teamService.update(id, name, title, member.getId());
        return MessageResult.success(team);
    }

    /**
     * 查询团队信息
     *
     * @param id
     * @return Team
     * @author ouyang
     * @version v1
     * @summary 查询团队信息
     * @since 2016-07-06 17:09:50
     */
    @RequestMapping(value = "/v1/team/{id}", method = RequestMethod.GET)
    public MessageResult<Team> add(@PathVariable(value = "id") Integer id) {
        Team team = teamService.select(id);
        return MessageResult.success(team);
    }


    @RequestMapping(value = "/v1/team/{id}/member/{memberId}", method = RequestMethod.POST)
    public MessageResult<Integer> addMember(
            HttpServletRequest request,
            @PathVariable(value = "id") Integer id,
            @PathVariable(value = "memberId") Integer memberId) {

        Member member = loginService.getMember(request);

        int row = teamService.insertMember(id, memberId, member.getId());

        return MessageResult.success(row);
    }

    @RequestMapping(value = "/v1/team/{id}/member/{memberId}", method = RequestMethod.DELETE)
    public MessageResult<Integer> delete(
            HttpServletRequest request,
            @PathVariable(value = "id") Integer id,
            @PathVariable(value = "memberId") Integer memberId) {

        Member member = loginService.getMember(request);

        int row = teamService.deleteMember(id, memberId, member.getId());

        return MessageResult.success(row);
    }

}
