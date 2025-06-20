package com.laboratory.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 基础视图参数
 *
 * @author wb
 * @since 2024-08-25
 */

@Data
public class BaseVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "创建人")
    private String createAt;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private String updateAt;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
