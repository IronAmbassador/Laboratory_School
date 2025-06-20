package com.laboratory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laboratory.model.dto.device.DeviceQueryDto;
import com.laboratory.model.entity.Device;
import com.laboratory.model.vo.device.DeviceVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
public interface DeviceService extends IService<Device> {
    List<DeviceVo> getAll(Integer pIndex, Integer pSize, DeviceQueryDto deviceQueryDto);

    void exportData(HttpServletResponse response);

    void importData(String filePath);

    Long getTotal();

    Long getRepairTotal();

    List<Long> getRepairCount();
}
