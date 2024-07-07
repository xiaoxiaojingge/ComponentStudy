package com.itjing.mapstruct;

import com.itjing.pojo.Room;
import com.itjing.vo.RoomVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author lijing
 * @date 2021年11月29日 10:44
 * @description
 */
@Mapper
public interface RoomConverter {

	RoomConverter INSTANCE = Mappers.getMapper(RoomConverter.class);

	@Mappings({ @Mapping(source = "id", target = "id"),
			@Mapping(source = "time", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
			// 对象的层级映射
			@Mapping(source = "student.id", target = "studentId", dateFormat = "yyyy-MM-dd HH:mm:ss"),
			@Mapping(source = "student.name", target = "studentName", dateFormat = "yyyy-MM-dd HH:mm:ss"),
			// 忽略这个字段, vo里面有, pojo里面没有
			@Mapping(target = "ext", ignore = true) })
	RoomVo building2Vo(Room room);

}
