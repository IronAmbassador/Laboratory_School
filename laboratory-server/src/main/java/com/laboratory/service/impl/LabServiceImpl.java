package com.laboratory.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laboratory.mapper.LabMapper;
import com.laboratory.model.dto.lab.LabQueryDto;
import com.laboratory.model.entity.Info;
import com.laboratory.service.LabService;
import org.springframework.stereotype.Service;

/**
 * 实验室业务逻辑
 */
@Service
public class LabServiceImpl extends ServiceImpl<LabMapper, Info>
        implements LabService {

    @Override
    public PageInfo<Info> getAll(Integer pIndex, Integer pSize, LabQueryDto labQueryDto) {
        // 开启分页
        PageHelper.startPage(pIndex, pSize);

        // 条件参数
        String name = labQueryDto.getName();

        // 构造条件
        LambdaQueryWrapper<Info> qw = new LambdaQueryWrapper<>();
        qw.like(StrUtil.isNotBlank(name), Info::getName, labQueryDto.getName());

        // 根据修改时间倒序
        qw.orderByDesc(Info::getUpdateTime);

        return new PageInfo<>(baseMapper.selectList((qw)));
    }
}




