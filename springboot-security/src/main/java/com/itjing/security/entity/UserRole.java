package com.itjing.security.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author lijing
 * @since 2022-08-25
 */
@Getter
@Setter
@TableName("t_user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	private Long userId;

	private Long roleId;

	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	private LocalDateTime updateTime;

}
