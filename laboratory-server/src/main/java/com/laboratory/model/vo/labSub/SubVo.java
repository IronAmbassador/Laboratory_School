package com.laboratory.model.vo.labSub;

import com.laboratory.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 实验室分室视图对象
 *
 * @author lucky
 */


@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "实验室分室视图对象")
@Data
public class SubVo extends BaseVO {

    @ApiModelProperty(value = "实验室名称")
    private String name;

    @ApiModelProperty(value = "实验室分室名称")
    private String labName;

    @ApiModelProperty(value = "实验室分室类型")
    private String labType;

    @ApiModelProperty(value = "实验室状态")
    private Integer labStatus;

    @ApiModelProperty(value = "实验室分室创建年份")
    private String labCreateYear;

    @ApiModelProperty(value = "实验室分室面积")
    private Integer labArea;

    @ApiModelProperty(value = "实验室分室地址")
    private String labAddr;

    @ApiModelProperty(value = "实验室分室座位数")
    private Integer labSeat;

    @ApiModelProperty(value = "实验室分室专业")
    private String labMajor;
}
