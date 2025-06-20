package com.laboratory.model.vo.open;

import com.laboratory.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 实验室开放视图参数
 *
 * @author lucky
 */

@ApiModel(value = "实验室开放视图对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class OpenVo extends BaseVO {

    @ApiModelProperty(value = "实验室")
    private String labName;

    @ApiModelProperty(value = "负责人")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "实验室开放时间")
    private Date startTime;

    @ApiModelProperty(value = "实验室关闭时间")
    private Date stopTime;
}
