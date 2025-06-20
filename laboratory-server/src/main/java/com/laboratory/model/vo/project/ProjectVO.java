package com.laboratory.model.vo.project;

import com.laboratory.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 项目视图对象
 *
 * @author wb
 * @since 2024-09-04
 */

@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "项目视图对象")
@Data
public class ProjectVO extends BaseVO {

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "项目启动时间")
    private Date startTime;

    @ApiModelProperty(value = "项目结束时间")
    private Date stopTime;

    @ApiModelProperty(value = "项目介绍")
    private String description;

    @ApiModelProperty(value = "项目成员")
    private String personNames;

}
