package com.laboratory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.model.dto.person.PersonQueryDto;
import com.laboratory.model.entity.Person;
import com.laboratory.model.vo.person.PersonVo;

import java.util.List;

/**
 * @Entity com.springboot.model.pojo.Person
 */
public interface PersonMapper extends BaseMapper<Person> {
    List<PersonVo> getAll(PersonQueryDto personQueryDto);

}




