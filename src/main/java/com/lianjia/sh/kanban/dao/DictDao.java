package com.lianjia.sh.kanban.dao;

import com.lianjia.sh.kanban.model.Dict;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ouyang
 * @since 2016-07-07 14:17
 */
@Repository
public class DictDao{

    @Autowired
    private SqlSession sqlSession;

    public Integer insert(
            String name, String ename, String desc, String key, String value, Long parentCode, Long code, Integer cm) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("ename", ename);
        paramMap.put("desc", desc);
        paramMap.put("key", key);
        paramMap.put("value", value);
        paramMap.put("parentCode", parentCode);
        paramMap.put("code", code);
        paramMap.put("cm", cm);

        sqlSession.insert("DictMapper.insert",paramMap);

        return (Integer) paramMap.get("id");
    }

    public int delete(Integer id, Integer um) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("um", um);
        return sqlSession.update("DictMapper.delete", paramMap);
    }

    public int update(
            Integer id, String name, String ename, String desc, Integer um) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("name", name);
        paramMap.put("ename", ename);
        paramMap.put("desc", desc);
        paramMap.put("um", um);

        return sqlSession.update("DictMapper.update", paramMap);
    }

    public Dict select(Integer id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return sqlSession.selectOne("DictMapper.select",paramMap);
    }

    public List<Dict> list(String name, String ename, Long parentCode, Long code) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("ename", ename);
        paramMap.put("parentCode", parentCode);
        paramMap.put("code", code);

        return sqlSession.selectList("DictMapper.list", paramMap);
    }

}
