package com.laboratory.listener;

import cn.hutool.core.bean.BeanUtil;
import com.laboratory.mapper.SubMapper;
import com.laboratory.model.entity.Sub;
import com.laboratory.model.vo.labSub.SubEEVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 实验室分室导入数据监听器
 *
 * @author lucky
 */
@Slf4j
public class SubListener extends BaseListener<SubEEVO, SubMapper> {


    public SubListener(SubMapper mapper) {
        super(mapper);
    }

    @Override
    protected void saveData() {
        log.info("{} >> {}条数据，开始存储数据库！", this.getClass().getSimpleName(), cachedDataList.size());
        cachedDataList.forEach(item -> {
            Sub labSub = BeanUtil.copyProperties(item, Sub.class, "id");
            // TODO 根据实验室名称查询实验室ID
            labSub.setLabId(1);
            mapper.insert(labSub);
        });
    }
}
