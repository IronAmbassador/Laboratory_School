package com.laboratory.listener;

import cn.hutool.core.bean.BeanUtil;
import com.laboratory.mapper.RepairMapper;
import com.laboratory.model.entity.DeviceRepair;
import com.laboratory.model.vo.repair.RepairEEVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 报修记录导入数据监听器
 *
 * @author wb
 * @since 2025-05-24
 */

@Slf4j
public class RepairListener extends BaseListener<RepairEEVO, RepairMapper> {
    public RepairListener(RepairMapper mapper) {
        super(mapper);
    }

    @Override
    protected void saveData() {
        log.info("{} >> {}条数据，开始存储数据库！", this.getClass().getSimpleName(), cachedDataList.size());
        cachedDataList.forEach(item -> {
            DeviceRepair repair = BeanUtil.copyProperties(item, DeviceRepair.class, "id");
            repair.setDeviceId(1);

            mapper.insert(repair);
        });
    }
}
