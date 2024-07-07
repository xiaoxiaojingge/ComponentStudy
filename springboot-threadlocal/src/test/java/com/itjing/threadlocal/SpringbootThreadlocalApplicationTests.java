package com.itjing.threadlocal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

@SpringBootTest
class SpringbootThreadlocalApplicationTests {

	class User {

		private Long id;

		private String name;

		public User() {
		}

		public User(Long id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public String toString() {
			return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
		}

	}

	@Test
	public void testMemoryLeak() throws Exception {
		ThreadLocal<User> threadLocal = new ThreadLocal<>();
		threadLocal.set(new User(System.currentTimeMillis(), "lijing"));
		threadLocal = null;
		System.gc();
		printEntryInfo();
	}

	private void printEntryInfo() throws Exception {
		Thread currentThread = Thread.currentThread();
		Class<? extends Thread> clz = currentThread.getClass();
		Field field = clz.getDeclaredField("threadLocals");
		field.setAccessible(true);
		Object threadLocalMap = field.get(currentThread);
		Class<?> tlmClass = threadLocalMap.getClass();
		Field tableField = tlmClass.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] arr = (Object[]) tableField.get(threadLocalMap);
		for (Object o : arr) {
			if (o != null) {
				Class<?> entryClass = o.getClass();
				Field valueField = entryClass.getDeclaredField("value");
				Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
				valueField.setAccessible(true);
				referenceField.setAccessible(true);
				System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
			}
		}
	}

	private static final ThreadLocal<String> RESOURCE = new ThreadLocal<>();

	@Test
	public void multiThread() {
		Thread thread1 = new Thread(() -> {
			RESOURCE.set("thread1");
			System.gc();
			try {
				printEntryInfo();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		Thread thread2 = new Thread(() -> {
			RESOURCE.set("thread2");
			System.gc();
			try {
				printEntryInfo();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		thread1.start();
		thread2.start();
	}

}
