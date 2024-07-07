package com.itjing.study.valid;

import com.itjing.entity.User;
import com.itjing.service.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author lijing
 * @date 2022年06月01日 15:29
 * @description
 */
@Slf4j
public class UserValidation<T extends Annotation> implements ConstraintValidator<T, User> {

	protected Predicate<User> predicate = c -> true;

	@Resource
	protected UserRepository userRepository;

	@Override
	public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
		return userRepository == null || predicate.test(user);
	}

	/**
	 * 校验用户是否唯一 即判断数据库是否存在当前新用户的信息，如用户名，手机，邮箱
	 */
	public static class UniqueUserValidator extends UserValidation<UniqueUser> {

		@Override
		public void initialize(UniqueUser uniqueUser) {
			predicate = c -> !userRepository.existsByUserNameOrEmailOrTelphone(c.getUserName(), c.getEmail(),
					c.getTelphone());
		}

	}

	/**
	 * 校验是否与其他用户冲突 将用户名、邮件、电话改成与现有完全不重复的，或者只与自己重复的，就不算冲突
	 */
	public static class NotConflictUserValidator extends UserValidation<NotConflictUser> {

		@Override
		public void initialize(NotConflictUser notConflictUser) {
			predicate = c -> {
				log.info("user detail is {}", c);
				List<User> list = userRepository.findByUserNameOrEmailOrTelphone(c.getUserName(), c.getEmail(),
						c.getTelphone());
				// 将用户名、邮件、电话改成与现有完全不重复的，或者只与自己重复的，就不算冲突
				return list.isEmpty() || (list.size() == 1 && list.get(0).getId().equals(c.getId()));
			};
		}

	}

}
