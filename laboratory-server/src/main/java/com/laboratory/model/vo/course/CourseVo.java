package com.laboratory.model.vo.course;

import com.laboratory.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 课程视图参数
 *
 * @author lucky
 */

@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "课程视图参数")
@Data
public class CourseVo extends BaseVO {

    @ApiModelProperty(value = "课程名称")
    private String name;

    @ApiModelProperty(value = "教师")
    private String teacher;

    @ApiModelProperty(value = "上课时间")
    private Date upTime;

    @ApiModelProperty(value = "下课时间")
    private Date downTime;

    @ApiModelProperty(value = "实验室")
    private String labName;

}
