package com.lianjia.sh.kanban.service;

import com.lianjia.sh.kanban.dao.MemberDao;
import com.lianjia.sh.kanban.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ouyang
 * @since 2016-07-08 15:23
 */
@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    public List<Member> list(Integer teamId) {
        return memberDao.list(teamId);
    }

    public Member select(Integer userId) {
        return memberDao.select(userId);
    }
}
