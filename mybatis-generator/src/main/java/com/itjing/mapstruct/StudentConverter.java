package com.itjing.mapstruct;

import com.itjing.pojo.Student;
import com.itjing.vo.StudentVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author lijing
 * @date 2021年11月29日 10:47
 * @description
 */
@Mapper
public interface StudentConverter {

	StudentConverter INSTANCE = Mappers.getMapper(StudentConverter.class);

	// source是转换的源对象, target要转成的对象
	@Mappings({ @Mapping(source = "id", target = "studentId"),
			@Mapping(source = "gender", target = "gender", qualifiedByName = "convertGender"),
			@Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss"),
			@Mapping(source = "home", target = "homeLocation"), })
	StudentVo student2Vo(Student student);

	@Named("convertGender")
	default String convertGender(Integer gender) {
		if (gender == 0) {
			return "女";
		}
		return "男";
	}

}
