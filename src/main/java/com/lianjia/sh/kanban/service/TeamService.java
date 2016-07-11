package com.lianjia.sh.kanban.service;

import com.lianjia.sh.kanban.dao.TeamDao;
import com.lianjia.sh.kanban.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author ouyang
 * @since 2016-07-06 16:24
 */
@Service
public class TeamService {

    @Autowired
    private TeamDao teamDao;

    public List<Team> list(String name) {

        return teamDao.list(name);

    }

    public Team insert(String name, String title, Integer cm) {

        Assert.hasLength(name);
        Assert.hasLength(title);
        Assert.isTrue(name.length() < 25);
        Assert.isTrue(name.length() > 1);
        Assert.isTrue(title.length() < 50);
        Assert.isTrue(title.length() > 1);

        List<Team> list = list(name);

        Assert.isTrue(list.size() == 0, "team name exist");

        Integer id = teamDao.insert(name, title, cm);

        //将创建者加入团队
        this.insertMember(id, cm, cm);

        return select(id);
    }

    public void delete(Integer id, Integer cm) {
        teamDao.delete(id, cm);
    }

    public Team update(Integer id, String name, String title, Integer cm) {

        Assert.notNull(id);
        Assert.hasLength(name);
        Assert.hasLength(title);
        Assert.isTrue(name.length() < 20);
        Assert.isTrue(title.length() < 50);

        teamDao.update(id, name, title, cm);

        return select(id);
    }

    public Team select(Integer id) {

        Assert.notNull(id);

        return teamDao.select(id);
    }

    public int insertMember(Integer id, Integer memberId, Integer cm) {
        return teamDao.insertMember(id,memberId,cm);
    }

    public int deleteMember(Integer id, Integer memberId, Integer um) {
        return teamDao.deleteMember(id,memberId,um);
    }
}
