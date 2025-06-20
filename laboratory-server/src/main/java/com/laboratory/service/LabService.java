package com.laboratory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.laboratory.model.dto.lab.LabQueryDto;
import com.laboratory.model.entity.Info;

/**
 *
 */
public interface LabService extends IService<Info> {

    PageInfo<Info> getAll(Integer pIndex, Integer pSize, LabQueryDto labQueryDto);
}
