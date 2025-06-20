package com.laboratory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.laboratory.listener.RepairListener;
import com.laboratory.mapper.RepairMapper;
import com.laboratory.model.dto.repair.RepairQueryDto;
import com.laboratory.model.entity.DeviceRepair;
import com.laboratory.model.vo.repair.RepairEEVO;
import com.laboratory.model.vo.repair.RepairVo;
import com.laboratory.service.RepairService;
import com.laboratory.utils.CommonUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @author lucky
* @description 针对表【device_repair(设备报修表)】的数据库操作Service实现
* @createDate 2024-04-21 16:40:53
*/
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, DeviceRepair>
    implements RepairService{

    @Override
    public List<RepairVo> getAll(Integer pIndex, Integer pSize, RepairQueryDto repairQueryDto) {
        // 开启分页
        PageHelper.startPage(pIndex, pSize);

        return baseMapper.getAll(repairQueryDto);
    }

    @Override
    public void exportData(HttpServletResponse response) {
        CommonUtils.exportDataToExcel(response, "设备报修信息", RepairEEVO.class, baseMapper.getAll( null));
    }

    @Override
    public void importData(String filePath) {
        CommonUtils.importDataToExcel(filePath, RepairEEVO.class, new RepairListener(baseMapper));
    }

    @Override
    public boolean save(DeviceRepair entity) {
        entity.setStatus(2);
        return super.save(entity);
    }
}




