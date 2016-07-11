package com.lianjia.sh.kanban.dao;

import com.lianjia.sh.kanban.model.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ouyang
 * @since 2016-07-10 17:12
 */
@Repository
public class MemberDao {

    @Autowired
    private SqlSession sqlSession;

    public Integer insert(Integer userId, String name, String email, Integer cm) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("name", name);
        paramMap.put("email", email);
        paramMap.put("cm", cm);

        sqlSession.insert("MemberMapper.insert", paramMap);

        return (Integer) paramMap.get("id");
    }

    public List<Member> list(Integer teamId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("teamId", teamId);
        return sqlSession.selectList("MemberMapper.list",paramMap);
    }

    public Member select(Integer userId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        return sqlSession.selectOne("MemberMapper.select",paramMap);
    }
}
