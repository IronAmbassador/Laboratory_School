package com.laboratory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laboratory.model.dto.open.OpenQueryDto;
import com.laboratory.model.entity.Open;
import com.laboratory.model.vo.open.OpenVo;

import java.util.List;

/**
 *
 */
public interface OpenService extends IService<Open> {
    List<OpenVo> getAll(Integer pIndex, Integer pSize, OpenQueryDto openQueryDto);


    @Override
    boolean save(Open entity);

    @Override
    boolean updateById(Open entity);
}