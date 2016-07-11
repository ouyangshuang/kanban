package com.lianjia.sh.kanban.service;

import com.lianjia.sh.kanban.bean.DictEnum;
import com.lianjia.sh.kanban.dao.CaseDao;
import com.lianjia.sh.kanban.model.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @author ouyang
 * @since 2016-07-08 13:24
 */
@Service
public class CaseService {

    @Autowired
    private CaseDao caseDao;

    @Autowired
    private DictService dictService;

    public Case insert(
            String name,
            Integer chandaoId,
            String desc,
            Integer type,
            Integer level,
            Date transferExpectTime,
            Integer cm) {

        Assert.notNull(name);
        Assert.notNull(chandaoId);
        Assert.notNull(transferExpectTime);
        Assert.notNull(cm);

        Assert.isNull(dictService.selectKeyMap(DictEnum.需求类型).containsKey(type.toString()));
        Assert.isNull(dictService.selectKeyMap(DictEnum.需求优先级).containsKey(level.toString()));

        Integer id = caseDao.insert(name, chandaoId, desc, type, level, transferExpectTime, cm);

        return this.select(id);
    }

    public Case select(Integer id) {
        return caseDao.select(id);
    }

}
