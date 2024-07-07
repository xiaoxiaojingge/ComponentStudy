package com.itjing.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author lijing
 * @date 2021年11月20日 13:59
 * @description 编写Listener
 */

public class MyListener implements HttpSessionListener {

	// 根据session，监听在线人数
	public static int online = 0;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("sessionCreated");
		online++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("sessionDestroyed");
	}

}
