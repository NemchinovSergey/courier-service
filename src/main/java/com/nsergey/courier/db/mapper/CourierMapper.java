package com.nsergey.courier.db.mapper;

import com.nsergey.courier.db.model.Courier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CourierMapper {

    Courier findById(@Param("id") long id);

}
