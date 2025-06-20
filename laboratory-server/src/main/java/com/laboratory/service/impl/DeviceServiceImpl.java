package com.laboratory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.laboratory.listener.DeviceListener;
import com.laboratory.mapper.DeviceMapper;
import com.laboratory.model.dto.device.DeviceQueryDto;
import com.laboratory.model.entity.Device;
import com.laboratory.model.vo.device.DeviceEEVO;
import com.laboratory.model.vo.device.DeviceVo;
import com.laboratory.service.DeviceService;
import com.laboratory.utils.CommonUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device>
    implements DeviceService{

    private static final Integer USE_STATUS = 0;

    @Override
    public List<DeviceVo> getAll(Integer pIndex, Integer pSize, DeviceQueryDto deviceQueryDto) {
        PageHelper.startPage(pIndex, pSize);
        return baseMapper.getAll(deviceQueryDto);
    }

    @Override
    public void exportData(HttpServletResponse response) {
        CommonUtils.exportDataToExcel(response, "实验室设备", DeviceEEVO.class, baseMapper.getExportData());
    }

    @Override
    public void importData(String filePath) {
        CommonUtils.importDataToExcel(filePath, DeviceEEVO.class, new DeviceListener(baseMapper));
    }

    @Override
    public Long getTotal() {
        return baseMapper.selectCount(null);
    }

    @Override
    public Long getRepairTotal() {
        LambdaQueryWrapper<Device> lqw = new LambdaQueryWrapper<>();
        lqw.ne(Device::getDeviceStatus, USE_STATUS);

        return baseMapper.selectCount(lqw);
    }

    @Override
    public List<Long> getRepairCount() {
        return baseMapper.getRepairCount();
    }
}




