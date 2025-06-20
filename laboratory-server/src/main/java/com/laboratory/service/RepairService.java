package com.laboratory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laboratory.model.dto.repair.RepairQueryDto;
import com.laboratory.model.entity.DeviceRepair;
import com.laboratory.model.vo.repair.RepairVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @author lucky
* @description 针对表【device_repair(设备报修表)】的数据库操作Service
* @createDate 2024-04-21 16:40:53
*/
public interface RepairService extends IService<DeviceRepair> {

    List<RepairVo> getAll(Integer pIndex, Integer pSize, RepairQueryDto repairQueryDto);

    void exportData(HttpServletResponse response);

    void importData(String filePath);
}
