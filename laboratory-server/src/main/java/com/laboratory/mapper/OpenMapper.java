package com.laboratory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.model.dto.open.OpenQueryDto;
import com.laboratory.model.entity.Open;
import com.laboratory.model.vo.open.OpenVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface OpenMapper extends BaseMapper<Open> {
    List<OpenVo> getAll(OpenQueryDto openQueryDto);

    /**
     * 检查实验室在指定时间段是否开放
     * @param labId 实验室ID
     * @param startTime 预约开始时间
     * @param endTime 预约结束时间
     * @return 开放记录数量（>0表示开放）
     */
    @Select("SELECT COUNT(*) FROM lab_open " +
            "WHERE lab_id = #{labId} " +
            "AND is_deleted = 0 " +
            "AND start_time <= #{startTime} " +
            "AND stop_time >= #{endTime}")
    int countOpenByTime(@Param("labId") Integer labId,
                        @Param("startTime") Date startTime,
                        @Param("endTime") Date endTime);
}