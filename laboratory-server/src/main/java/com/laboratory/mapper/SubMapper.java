package com.laboratory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.model.dto.labSub.SubQueryDTO;
import com.laboratory.model.entity.Sub;
import com.laboratory.model.vo.labSub.SubEEVO;
import com.laboratory.model.vo.labSub.SubVo;

import java.util.List;

/**
 * @Entity com.springboot.model.pojo.Sub
 */
public interface SubMapper extends BaseMapper<Sub> {

    List<SubVo> getAll(SubQueryDTO labSubQueryDTO);

    List<SubEEVO> getExportDate();

    List<Long> selectWeekCount();
}




