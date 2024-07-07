package com.itjing.study.函数式接口消灭if和else;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author lijing
 * @date 2022年06月01日 11:35
 * @description
 */
@Slf4j
public class VUtils {

	/**
	 * 如果参数为true抛出异常
	 * @param b
	 * @return
	 **/
	public static ThrowExceptionFunction isTrue(boolean b) {

		return (errorMessage) -> {
			if (b) {
				throw new RuntimeException(errorMessage);
			}
		};
	}

	/**
	 * 参数为true或false时，分别进行不同的操作
	 * @param b
	 * @return
	 **/
	public static BranchHandle isTrueOrFalse(boolean b) {

		return (trueHandle, falseHandle) -> {
			if (b) {
				trueHandle.run();
			}
			else {
				falseHandle.run();
			}
		};
	}

	/**
	 * 参数为true或false时，分别进行不同的操作
	 * @param str
	 * @return
	 **/
	public static PresentOrElseHandler<?> isBlankOrNoBlank(String str) {

		return (consumer, runnable) -> {
			if (StringUtils.isEmpty(str)) {
				runnable.run();
			}
			else {
				consumer.accept(str);
			}
		};
	}

	public static void main(String[] args) {
		// 当传入的参数为true时抛出异常。
		// VUtils.isTrue(true).throwMessage("抛出异常");

		VUtils.isTrueOrFalse(true).trueOrFalseHandle(() -> {
			log.info("true");
		}, () -> {
			log.info("false");
		});

		VUtils.isBlankOrNoBlank("").presentOrElseHandle(System.out::println, () -> {
			log.info("blank");
		});

	}

}
