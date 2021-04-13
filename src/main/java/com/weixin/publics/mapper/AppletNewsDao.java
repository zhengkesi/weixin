package com.weixin.publics.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: xiaodong
 * @Date: 2020/12/14 15:02
 */
@Mapper
public interface AppletNewsDao {
    @Select("SELECT XXMC FROM zxxx0101")
     List<Map<String, Object>> newSelectAll();
}
