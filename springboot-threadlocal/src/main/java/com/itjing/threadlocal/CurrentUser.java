package com.itjing.threadlocal;

import com.itjing.threadlocal.entity.User;

/**
 * @author lijing
 * @date 2022年05月31日 19:08
 * @description 线程当前用户信息
 */
public class CurrentUser {

	private static final ThreadLocal<User> USER = new ThreadLocal<>();

	private static final ThreadLocal<Long> USER_ID = new ThreadLocal<>();

	private static final InheritableThreadLocal<User> INHERITABLE_USER = new InheritableThreadLocal<>();

	private static final InheritableThreadLocal<Long> INHERITABLE_USER_ID = new InheritableThreadLocal<>();

	public static void setUser(User user) {
		USER.set(user);
		INHERITABLE_USER.set(user);
	}

	public static void setUserId(Long id) {
		USER_ID.set(id);
		INHERITABLE_USER_ID.set(id);
	}

	public static User user() {
		return USER.get();
	}

	public static User inheritableUser() {
		return INHERITABLE_USER.get();
	}

	public static Long inheritableUserId() {
		return INHERITABLE_USER_ID.get();
	}

	public static Long userId() {
		return USER_ID.get();
	}

	public static void removeAll() {
		USER.remove();
		USER_ID.remove();
		INHERITABLE_USER.remove();
		INHERITABLE_USER_ID.remove();
	}

}
