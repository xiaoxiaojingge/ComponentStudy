package com.itjing.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author lijing
 * @date 2021年11月29日 10:45
 * @description
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class RoomVo {

	private Integer id;

	private String createTime;

	private Integer studentId;

	private String studentName;

	private String ext;

}