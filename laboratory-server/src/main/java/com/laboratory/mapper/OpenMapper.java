package com.laboratory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.model.dto.open.OpenQueryDto;
import com.laboratory.model.entity.Open;
import com.laboratory.model.vo.open.OpenVo;

import java.util.List;

/**
 * @Entity com.springboot.model.pojo.Open
 */
public interface OpenMapper extends BaseMapper<Open> {
    List<OpenVo> getAll(OpenQueryDto openQueryDto);

}




