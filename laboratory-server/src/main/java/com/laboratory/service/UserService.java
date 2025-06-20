package com.laboratory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.laboratory.model.dto.user.UserUpdPwdDto;
import com.laboratory.model.dto.user.UserLoginDto;
import com.laboratory.model.entity.User;
import com.laboratory.model.dto.user.UserQueryDto;

import java.util.Map;

/**
 *
 */
public interface UserService extends IService<User> {

    Map<String, Object> login(UserLoginDto userLoginDto);

    PageInfo<User> getAll(Integer pIndex, Integer pSize, UserQueryDto userQueryDto);

    User getInfo(String token);

    Boolean updatePwd(UserUpdPwdDto userUpdPwdDto);
}
