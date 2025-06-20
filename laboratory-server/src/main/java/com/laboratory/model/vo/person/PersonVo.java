package com.laboratory.model.vo.person;

import com.laboratory.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 人员视图参数
 *
 * @author lucky
 */

@ApiModel(value = "人员视图对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class PersonVo extends BaseVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "实验室")
    private String labName;
}
