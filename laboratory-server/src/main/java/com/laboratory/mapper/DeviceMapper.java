package com.laboratory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.model.dto.device.DeviceQueryDto;
import com.laboratory.model.entity.Device;
import com.laboratory.model.vo.device.DeviceEEVO;
import com.laboratory.model.vo.device.DeviceVo;

import java.util.List;

/**
 * @Entity com.springboot.model.pojo.Device
 */
public interface DeviceMapper extends BaseMapper<Device> {
    List<DeviceVo> getAll(DeviceQueryDto deviceQueryDto);

    List<DeviceEEVO> getExportData();

    List<Long> getRepairCount();
}




