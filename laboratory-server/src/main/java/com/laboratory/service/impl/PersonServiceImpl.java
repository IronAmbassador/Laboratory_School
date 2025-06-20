package com.laboratory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.laboratory.mapper.PersonMapper;
import com.laboratory.model.dto.person.PersonQueryDto;
import com.laboratory.model.entity.Person;
import com.laboratory.model.vo.person.PersonVo;
import com.laboratory.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person>
    implements PersonService{

    @Autowired
    private PersonMapper mapper;

    @Override
    public List<PersonVo> getAll(Integer pIndex, Integer pSize, PersonQueryDto personQueryDto) {
        PageHelper.startPage(pIndex, pSize);
        return mapper.getAll(personQueryDto);
    }
}




