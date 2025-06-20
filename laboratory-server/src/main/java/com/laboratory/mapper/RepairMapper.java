package com.laboratory.mapper;

import com.laboratory.model.dto.repair.RepairQueryDto;
import com.laboratory.model.entity.DeviceRepair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.model.vo.repair.RepairVo;

import java.util.List;

/**
* @author lucky
* @description 针对表【device_repair(设备报修表)】的数据库操作Mapper
* @createDate 2024-04-21 16:40:53
*/
public interface RepairMapper extends BaseMapper<DeviceRepair> {

    List<RepairVo> getAll(RepairQueryDto repairQueryDto);
}




