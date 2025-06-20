package com.laboratory.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.laboratory.mapper.BookingMapper;
import com.laboratory.model.dto.labBooking.BookingProcessDTO;
import com.laboratory.model.dto.labBooking.BookingQueryDTO;
import com.laboratory.model.entity.Booking;
import com.laboratory.model.vo.labBooking.BookingEEVO;
import com.laboratory.model.vo.labBooking.BookingVO;
import com.laboratory.service.BookingService;
import com.laboratory.utils.CommonUtils;
import com.laboratory.utils.SecurityHolderUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lucky
 * @description 针对表【lab_booking】的数据库操作Service实现
 * @createDate 2024-07-18 20:11:27
 */
@Service
public class BookingServiceImpl extends ServiceImpl<BookingMapper, Booking>
        implements BookingService {

    @Override
    public boolean save(Booking entity) {
        entity.setUserId(SecurityHolderUtils.getUserId());
        return super.save(entity);
    }

    @Override
    public List<BookingVO> getAll(Integer pIndex, Integer pSize, BookingQueryDTO labBookingQueryDTO) {
        // 开启分页
        PageHelper.startPage(pIndex, pSize);

        // 预约时间
        String[] bookingTimes = labBookingQueryDTO.getBookingTimes();
        if (ObjectUtil.isNotEmpty(bookingTimes)) {
            labBookingQueryDTO.setStartTime(bookingTimes[0]);
            labBookingQueryDTO.setEndTime(bookingTimes[1]);
        }

        // 管理员查询所有
        if (labBookingQueryDTO.getRoleId() == 1) {
            labBookingQueryDTO.setUserId(null);
        }

        return baseMapper.getAll(labBookingQueryDTO);
    }

    @Override
    public void exportData(HttpServletResponse response) {
        CommonUtils.exportDataToExcel(response, "实验室预约记录", BookingEEVO.class, baseMapper.getAll(null));
    }

    @Override
    public Boolean process(BookingProcessDTO labBookingProcessDTO) {
        Booking labBooking = new Booking();
        labBooking.setId(labBookingProcessDTO.getId());
        labBooking.setBookingStatus(labBookingProcessDTO.getBookingStatus());
        return super.updateById(labBooking);
    }
}




