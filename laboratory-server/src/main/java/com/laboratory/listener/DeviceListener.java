package com.laboratory.listener;

import cn.hutool.core.bean.BeanUtil;
import com.laboratory.mapper.DeviceMapper;
import com.laboratory.model.entity.Device;
import com.laboratory.model.vo.device.DeviceEEVO;
import lombok.extern.slf4j.Slf4j;


/**
 * 设备导入数据监听器
 *
 * @author lucky
 */
@Slf4j
public class DeviceListener extends BaseListener<DeviceEEVO, DeviceMapper> {
    public DeviceListener(DeviceMapper mapper) {
        super(mapper);
    }

    @Override
    protected void saveData() {
        log.info("{} >> {}条数据，开始存储数据库！", this.getClass().getSimpleName(), cachedDataList.size());
        cachedDataList.forEach(item -> {
            Device device = BeanUtil.copyProperties(item, Device.class, "id");
            // TODO 根据实验室名称查询实验室ID
            device.setLabId(1);
            mapper.insert(device);
        });
    }
}
