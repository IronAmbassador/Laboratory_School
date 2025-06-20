package com.laboratory.mapper;

import com.laboratory.model.dto.labBooking.BookingQueryDTO;
import com.laboratory.model.entity.Booking;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.model.vo.labBooking.BookingVO;

import java.util.List;

/**
* @author lucky
* @description 针对表【lab_booking】的数据库操作Mapper
* @createDate 2024-07-18 20:11:27
* @Entity com.laboratory.model.entity.Booking
*/
public interface BookingMapper extends BaseMapper<Booking> {

    List<BookingVO> getAll(BookingQueryDTO labBookingQueryDTO);
}




