package com.laboratory.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laboratory.exception.LabException;
import com.laboratory.mapper.UserMapper;
import com.laboratory.model.dto.user.UserLoginDto;
import com.laboratory.model.dto.user.UserQueryDto;
import com.laboratory.model.dto.user.UserUpdPwdDto;
import com.laboratory.model.entity.User;
import com.laboratory.result.ResultCodeEnum;
import com.laboratory.service.UserService;
import com.laboratory.utils.JwtUtils;
import com.laboratory.utils.SecurityHolderUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public Map<String, Object> login(UserLoginDto userLoginDto) {
        String username = userLoginDto.getName();

        // 根据用户输入的用户名得到用户信息
        User u = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(StrUtil.isNotBlank(username), User::getUsername, username));

        // 判断用户是否为空
        if (ObjectUtil.isNull(u)) {
            throw new LabException(ResultCodeEnum.USERNAME_NOT_FOUND);
        }

        // 判断用户状态是否被禁用
        if (u.getStatus() == 1) {
            throw new LabException(ResultCodeEnum.USER_DISABLED);
        }

        // 判断用户输入的密码和当前查询的用户密码是否一致
        if (!u.getPassword().equals(DigestUtil.md5Hex(userLoginDto.getPwd()))) {
            throw new LabException(ResultCodeEnum.PASSWORD_ERROR);
        }

        Map<String, String> claimMap = new HashMap<>();
        claimMap.put("userId", u.getId().toString());
        claimMap.put("username", u.getUsername());
        claimMap.put("nickname", u.getNickname());

        String token = JwtUtils.createToken(claimMap);

        // 返回登录信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);

        return map;
    }

    @Override
    public PageInfo<User> getAll(Integer pIndex, Integer pSize, UserQueryDto userQueryDto) {
        // 开启分页
        PageHelper.startPage(pIndex, pSize);

        // 条件参数
        String username = userQueryDto.getName();
        Integer roleId = userQueryDto.getRoleId();
        String email = userQueryDto.getEmail();
        String sex = userQueryDto.getSex();
        Integer status = userQueryDto.getStatus();

        // 构造条件
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.like(StrUtil.isNotBlank(username), User::getUsername, username)
                .eq(StrUtil.isNotBlank(email), User::getUserEmail, email)
                .eq(ObjectUtil.isNotEmpty(roleId), User::getRoleId, roleId)
                .eq(StrUtil.isNotBlank(sex), User::getUserSex, sex)
                .eq(ObjectUtil.isNotEmpty(status), User::getStatus, status);

        // 根据修改时间倒序
        qw.orderByDesc(User::getUpdateTime);

        return new PageInfo<>(baseMapper.selectList((qw)));
    }

    @Override
    public User getInfo(String token) {
        DecodedJWT decoded = JwtUtils.verifyToken(token);
        Claim userId = decoded.getClaims().get("userId");

        User user = baseMapper.selectById(userId.asString());

        if (ObjectUtil.isNull(user)) {
            throw new LabException(ResultCodeEnum.DATA_ERROR);
        }

        return user;
    }

    @Override
    public boolean save(User entity) {
        // 密码加密
        entity.setPassword(DigestUtil.md5Hex(entity.getPassword()));
        return super.save(entity);
    }

    @Override
    public Boolean updatePwd(UserUpdPwdDto userUpdPwdDto) {
        // 查询用户
        User user = baseMapper.selectById(SecurityHolderUtils.getUserId());

        // 判断旧密码是否正确
        if (!DigestUtil.md5Hex(userUpdPwdDto.getOldPwd()).equals(user.getPassword())) {
            throw new LabException(ResultCodeEnum.OLD_PASSWORD_ERROR);
        } else {
            // 修改密码
            user.setPassword(DigestUtil.md5Hex(userUpdPwdDto.getNewPwd()));
            return baseMapper.updateById(user) > 0;
        }
    }


    @Override
    public boolean removeById(Serializable id) {
        Integer userId = (Integer) id;

        if(1 == userId){
            throw new LabException(ResultCodeEnum.ADMIN_NOT_DELETED);
        }

        if(userId.equals(SecurityHolderUtils.getUserId())){
            throw new LabException(ResultCodeEnum.CURRENT_USER_NOT_DELETED);
        }

        return super.removeById(id);
    }

    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        // 获取当前登录用户ID
        Integer currentUserId = SecurityHolderUtils.getUserId();

        // 过滤出可以安全删除的ID列表
        List<Integer> deletableIds = list.stream()
                .filter(id -> id instanceof Integer)
                .map(id -> (Integer) id)
                .filter(id -> !id.equals(1))                 // 排除管理员ID
                .filter(id -> !id.equals(currentUserId))     // 排除当前登录用户
                .collect(Collectors.toList());

        // 如果没有可删除的ID，直接返回true或者根据需求处理
        if (deletableIds.isEmpty()) {
            return true;
        }

        // 执行删除操作
        return super.removeBatchByIds(deletableIds);
    }
}




