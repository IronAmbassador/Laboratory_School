package com.laboratory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laboratory.model.dto.labSub.SubQueryDTO;
import com.laboratory.model.entity.Sub;
import com.laboratory.model.vo.labSub.SubVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
public interface SubService extends IService<Sub> {

    List<SubVo> getAll(Integer pIndex, Integer pSize, SubQueryDTO labSubQueryDTO);

    void exportData(HttpServletResponse response);

    void importData(String filePath);

    Long getLabTotal();

    Long getFreeTotal();

    List<Long> getUseCount();
}
