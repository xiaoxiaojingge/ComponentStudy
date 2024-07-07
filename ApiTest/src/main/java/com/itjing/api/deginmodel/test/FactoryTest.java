package com.itjing.api.deginmodel.test;

import com.itjing.api.deginmodel.abstractFactory.*;
import com.itjing.api.deginmodel.factory.Moveable;
import com.itjing.api.deginmodel.factory.PlaneFactory;
import com.itjing.api.deginmodel.factory.VehicleFactory;
import org.junit.Test;

/**
 *
 * 创建时间:2017-9-2下午5:51:06<br/>
 * 创建者:sanri<br/>
 * 功能:简单工厂测试<br/>
 */
public class FactoryTest {

	/**
	 *
	 * 功能:测试简单工厂<br/>
	 * 创建时间:2017-9-2下午5:51:50<br/>
	 * 作者：sanri<br/>
	 * <br/>
	 */
	@Test
	public void testSimpleFactory() {
		VehicleFactory vehicleFactory = new PlaneFactory();
		Moveable plane = vehicleFactory.create();
		plane.run();
	}

	@Test
	public void testAbstractFactory() {
		// AbstractFactory f = new DefaultFactory();
		AbstractFactory f = new MagicFactory();
		Vehicle v = f.createVehicle();
		v.run();
		Weapon w = f.createWeapon();
		w.shoot();
		Food a = f.createFood();
		a.printName();
	}

}
