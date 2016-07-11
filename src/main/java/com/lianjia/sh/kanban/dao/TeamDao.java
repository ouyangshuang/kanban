package com.lianjia.sh.kanban.dao;

import com.lianjia.sh.kanban.model.Team;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ouyang
 * @since 2016-07-06 17:13
 */
@Repository
public class TeamDao {

    private SqlSession sqlSession;

    public List<Team> list(String name) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return sqlSession.selectList("TeamMapper.list",paramMap);
    }

    public Integer insert(String name, String title, Integer cm) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("title", title);
        paramMap.put("cm", cm);

        sqlSession.insert("TeamMapper.insert", paramMap);

        return (Integer) paramMap.get("id");
    }

    public int delete(Integer id, Integer um) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("um", um);
        return sqlSession.update("TeamMapper.delete",paramMap);
    }

    public int update(Integer id, String name, String title, Integer um) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("name", name);
        paramMap.put("title", title);
        paramMap.put("um", um);
        return sqlSession.update("TeamMapper.update",paramMap);
    }

    public Team select(Integer id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return sqlSession.selectOne("TeamMapper.select", paramMap);
    }

    public int insertMember(Integer teamId, Integer memberId, Integer cm) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("teamId", teamId);
        paramMap.put("memberId", memberId);
        paramMap.put("cm", cm);
        return sqlSession.insert("TeamMapper.insertMember", paramMap);
    }

    public int deleteMember(Integer teamId, Integer memberId, Integer um) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("teamId", teamId);
        paramMap.put("memberId", memberId);
        paramMap.put("um", um);
        return sqlSession.update("TeamMapper.deleteMember", paramMap);
    }

}
