package com.itjing.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itjing.springboot.entity.PersonInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonInfoMapper extends BaseMapper<PersonInfo> {
}