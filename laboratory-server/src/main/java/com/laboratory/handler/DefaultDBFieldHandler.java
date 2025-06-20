package com.laboratory.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.laboratory.utils.SecurityHolderUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动设置创建人，创建时间，修改人，修改时间默认值
 * @author lucky
 */
@Component
public class DefaultDBFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createAt", SecurityHolderUtils.getUsername(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateAt", SecurityHolderUtils.getUsername(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", new Date(), metaObject);
        setFieldValByName("updateAt", SecurityHolderUtils.getUsername(), metaObject);
    }
}
