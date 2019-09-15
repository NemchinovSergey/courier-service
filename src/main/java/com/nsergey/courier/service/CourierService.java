package com.nsergey.courier.service;

import com.nsergey.courier.db.mapper.CourierMapper;
import com.nsergey.courier.db.model.Courier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CourierService {

    private final CourierMapper courierMapper;

    @Autowired
    public CourierService(CourierMapper courierMapper) {
        this.courierMapper = courierMapper;
    }

    public Courier findById(long courierId) {
        return courierMapper.findById(courierId);
    }
}
