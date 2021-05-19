package com.scau.ruan.hotel.service;

import com.scau.ruan.hotel.entity.RegisterRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.ruan.hotel.entity.VO.RegisterIn;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bangtidy
 * @since 2021-05-13
 */
public interface RegisterRecordService extends IService<RegisterRecord> {

    boolean regisiterIn(RegisterIn registerIn);

    RegisterRecord regisiterOut(Integer registerRecordId);
}
