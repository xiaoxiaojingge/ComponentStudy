package com.itjing.task.mapper;

import com.itjing.task.domain.DynamicJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DynamicJobMapper {

	int deleteByPrimaryKey(Integer jobId);

	int insert(DynamicJob record);

	int insertSelective(DynamicJob record);

	DynamicJob selectByPrimaryKey(Integer jobId);

	int updateByPrimaryKeySelective(DynamicJob record);

	int updateByPrimaryKey(DynamicJob record);

	DynamicJob selectByBusinessId(@Param("businessId") Integer businessId);

	List<DynamicJob> listJobsByStatus(Byte status);

}