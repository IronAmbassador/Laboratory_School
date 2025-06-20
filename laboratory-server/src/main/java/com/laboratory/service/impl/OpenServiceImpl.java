package com.laboratory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.laboratory.mapper.OpenMapper;
import com.laboratory.model.dto.open.OpenQueryDto;
import com.laboratory.model.entity.Open;
import com.laboratory.model.vo.open.OpenVo;
import com.laboratory.service.OpenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class OpenServiceImpl extends ServiceImpl<OpenMapper, Open>
    implements OpenService{

    @Autowired
    private OpenMapper mapper;

    @Override
    public List<OpenVo> getAll(Integer pIndex, Integer pSize, OpenQueryDto openQueryDto) {
        PageHelper.startPage(pIndex, pSize);
        return mapper.getAll(openQueryDto);
    }
}




