package com.lianjia.sh.kanban.dao;

import com.lianjia.sh.kanban.model.Case;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ouyang
 * @since 2016-07-08 15:13
 */
@Repository
public class CaseDao {

    @Autowired
    private SqlSession sqlSession;

    public Integer insert(
            String name,
            Integer chandaoId,
            String desc,
            Integer type,
            Integer level,
            Date transferExpectTime,
            Integer cm) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("chandaoId", chandaoId);
        paramMap.put("desc", desc);
        paramMap.put("type", type);
        paramMap.put("level", level);
        paramMap.put("transferExpectTime", transferExpectTime);
        paramMap.put("cm", cm);

        sqlSession.insert("CaseMapper.insert", paramMap);

        return (Integer) paramMap.get("id");
    }

    public Case select(Integer id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return sqlSession.selectOne("CaseMapper.select",paramMap);
    }
}
