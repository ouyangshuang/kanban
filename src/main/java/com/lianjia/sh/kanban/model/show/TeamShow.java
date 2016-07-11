package com.lianjia.sh.kanban.model.show;

import com.lianjia.sh.kanban.model.Member;
import com.lianjia.sh.kanban.model.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ouyang
 * @since 2016-07-08 09:24
 */
public class TeamShow extends Team {

    private List<Member> memberList = new ArrayList<>();

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

}
