package com.lianjia.sh.kanban.service;

import com.lianjia.sh.kanban.bean.DictEnum;
import com.lianjia.sh.kanban.dao.DictDao;
import com.lianjia.sh.kanban.model.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ouyang
 * @since 2016-07-07 14:16
 */
@Service
public class DictService  {

    @Autowired
    private DictDao dictDao;

    public List<Dict> list(
            String name, String ename, Long parentCode, Long code) {

        return dictDao.list(name, ename, parentCode, code);
    }

    public Map<String, String> selectKeyMap(DictEnum dictEnum) {
        List<Dict> list = this.list(null, null, dictEnum.getParentCode(), null);
        Map<String, String> map = new HashMap<>();
        list.forEach(dict -> map.put(dict.getKey(), dict.getValue()));
        return map;
    }

    public Set<Long> selectCode(DictEnum dictEnum) {
        List<Dict> list = this.list(null, null, dictEnum.getParentCode(), null);
        Set<Long> set = new HashSet<>();
        list.forEach(dict -> set.add(dict.getCode()));
        return set;
    }

    public Dict insert(
            String name,
            String ename,
            String desc,
            String key,
            String value,
            Long parentCode,
            Long code,
            Integer memberId) {
        Integer id = dictDao.insert(name, ename, desc, key, value, parentCode, code, memberId);
        return select(id);
    }

    public Dict update(
            Integer id, String name, String ename, String desc, Integer memberId) {
        dictDao.update(id, name, ename, desc, memberId);
        return select(id);
    }

    public void delete(Integer id, Integer memberId) {
        dictDao.delete(id, memberId);
    }

    public Dict select(Integer id) {
        return dictDao.select(id);
    }


}
