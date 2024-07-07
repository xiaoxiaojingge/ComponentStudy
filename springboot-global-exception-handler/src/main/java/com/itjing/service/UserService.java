package com.itjing.service;

import com.itjing.dto.UserDTO;
import com.itjing.entity.User;

/**
 * @author lijing
 * @date 2021年12月01日 16:25
 * @description
 */
public interface UserService {

	User add(UserDTO userDTO);

	User getById(String id);

	void marry(String age);

}
