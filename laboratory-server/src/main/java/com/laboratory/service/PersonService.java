package com.laboratory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laboratory.model.dto.person.PersonQueryDto;
import com.laboratory.model.entity.Person;
import com.laboratory.model.vo.person.PersonVo;

import java.util.List;

/**
 *
 */
public interface PersonService extends IService<Person> {
    List<PersonVo> getAll(Integer pIndex, Integer pSize, PersonQueryDto personQueryDto);

}
