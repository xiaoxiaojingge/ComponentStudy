package com.itjing.faker;

import com.github.javafaker.Faker;

import java.util.Locale;

/**
 * @author lijing
 * @date 2021年11月02日 14:19
 * @description
 */
public class TestFaker {

	public static void main(String[] args) {
		// 制造中国的假数据
		Faker fakerWithCN = new Faker(Locale.CHINA);
		for (int i = 0; i < 10; i++) {
			UserInfo userInfo = new UserInfo();
			userInfo.setRealName(fakerWithCN.name().fullName());
			userInfo.setCellPhone(fakerWithCN.phoneNumber().cellPhone());
			userInfo.setCity(fakerWithCN.address().city());
			userInfo.setStreet(fakerWithCN.address().streetAddress());
			userInfo.setUniversityName(fakerWithCN.university().name());
			System.out.println("userInfo = " + userInfo);
		}

		// 制造美国的假数据
		Faker fakerWithUS = new Faker(Locale.US);
		for (int i = 0; i < 10; i++) {
			UserInfo userInfo = new UserInfo();
			userInfo.setRealName(fakerWithUS.name().fullName());
			userInfo.setCellPhone(fakerWithUS.phoneNumber().cellPhone());
			userInfo.setCity(fakerWithUS.address().city());
			userInfo.setStreet(fakerWithUS.address().streetAddress());
			userInfo.setUniversityName(fakerWithUS.university().name());
			System.out.println("userInfo = " + userInfo);
		}
	}

}
