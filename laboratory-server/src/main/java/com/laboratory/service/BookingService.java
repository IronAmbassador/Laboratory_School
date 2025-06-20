package com.laboratory.service;

import com.laboratory.model.dto.labBooking.BookingProcessDTO;
import com.laboratory.model.dto.labBooking.BookingQueryDTO;
import com.laboratory.model.entity.Booking;
import com.baomidou.mybatisplus.extension.service.IService;
import com.laboratory.model.vo.labBooking.BookingVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @author lucky
* @description 针对表【lab_booking】的数据库操作Service
* @createDate 2024-07-18 20:11:27
*/
public interface BookingService extends IService<Booking> {

    List<BookingVO> getAll(Integer pIndex, Integer pSize, BookingQueryDTO labBookingQueryDTO);

    void exportData(HttpServletResponse response);

    Boolean process(BookingProcessDTO labBookingProcessDTO);
}
