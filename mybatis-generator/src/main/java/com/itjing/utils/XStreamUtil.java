package com.itjing.utils;

import cn.hutool.core.bean.BeanUtil;
import com.itjing.pojo.Teacher;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Map;

/**
 * @author lijing
 * @date 2021年12月06日 9:31
 * @description xml与bean互转工具类
 */
public class XStreamUtil {

	private static String XML_TAG = "<?xml version='1.0' encoding='UTF-8'?>";

	/**
	 * Description: 私有化构造
	 */
	private XStreamUtil() {
		super();
	}

	/**
	 * @return
	 * @Description 为每次调用生成一个XStream
	 * @Title getInstance
	 */
	public static XStream getInstance() {
		XStream xStream = new XStream(new DomDriver("UTF-8")) {
			/**
			 * 忽略xml中多余字段
			 */
			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new MapperWrapper(next) {
					@Override
					public boolean shouldSerializeMember(Class definedIn, String fieldName) {
						if (definedIn == Object.class) {
							return false;
						}
						return super.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};

		// 设置默认的安全校验
		XStream.setupDefaultSecurity(xStream);
		// 使用本地的类加载器
		xStream.setClassLoader(XStreamUtil.class.getClassLoader());
		// 允许所有的类进行转换
		xStream.addPermission(AnyTypePermission.ANY);
		return xStream;
	}

	/**
	 * @param xml
	 * @param clazz
	 * @return
	 * @Description 将xml字符串转化为java对象
	 * @Title xmlToBean
	 */
	public static <T> T xmlToBean(String xml, Class<T> clazz) {
		XStream xStream = getInstance();
		xStream.processAnnotations(clazz);
		Object object = xStream.fromXML(xml);
		T cast = clazz.cast(object);
		return cast;
	}

	/**
	 * @param object
	 * @return
	 * @Description 将java对象转化为xml字符串
	 * @Title beanToXml
	 */
	public static String beanToXml(Object object) {
		XStream xStream = getInstance();
		xStream.processAnnotations(object.getClass());
		// 剔除所有tab、制表符、换行符
		String xml = xStream.toXML(object).replaceAll("\\s+", " ");
		return xml;
	}

	/**
	 * @param object
	 * @return
	 * @Description 将java对象转化为xml字符串（包含xml头部信息）
	 * @Title beanToXml
	 */
	public static String beanToXmlWithTag(Object object) {
		String xml = XML_TAG + beanToXml(object);
		return xml;
	}

	/**
	 * xml字符串转换为 map
	 * @param strXML
	 * @return
	 */
	public static Map<String, Object> xmlToMap(String strXML) {
		Map<String, Object> map = XmlUtils.xmlToMap(strXML);
		return map;
	}

	public static void main(String[] args) throws DocumentException {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + "<Teacher>\n" + "\t<id>1</id>\n"
				+ "\t<name>lijing</name>\n" + "\t<gender>男</gender>\n" + "\t<age>24</age>\n" + "\t<home>江苏</home>\n"
				+ "\t<major>软件</major>\n" + "</Teacher>\t\n";
		Element rootElement = DocumentHelper.parseText(xml).getRootElement();
		String bodyXml = rootElement.asXML();
		// xml 转为 bean
		Teacher teacher = XStreamUtil.xmlToBean(bodyXml, Teacher.class);
		System.out.println(teacher);

		System.out.println("------------------------------------------");

		// bean 转为 xml
		// 参数1：待转的对象
		// 参数2：是否格式化
		// 参数3：是否忽略头部声明
		// 注意要加上注解：@XmlRootElement(name = "Teacher")
		String teacherXml1 = XmlUtils.toStr(teacher, true, false);
		String teacherXml2 = XStreamUtil.beanToXmlWithTag(teacher);
		System.out.println(teacherXml1);
		System.out.println(teacherXml2);

		System.out.println("------------------------------------------");

		// xml 转为 map
		Map<String, Object> map1 = XStreamUtil.xmlToMap(xml);
		Map<String, Object> map2 = XmlUtils.xmlToMap(xml);
		System.out.println(map1);
		System.out.println(map2);

		System.out.println("------------------------------------------");

		// map 转为 xml字符串
		// 参数1为待转map
		// 参数2为根标签名称，不加则不生成
		String mapStr = XmlUtils.mapToXmlStr(map1, "Teacher");
		System.out.println(mapStr);

		System.out.println("------------------------------------------");

		// bean 转为 map
		Map<String, Object> stringObjectMap1 = PdfUtil.bean2Map(teacher);
		Map<String, Object> stringObjectMap2 = BeanUtil.beanToMap(teacher);
		System.out.println(stringObjectMap1);
		System.out.println(stringObjectMap2);

		System.out.println("------------------------------------------");

		// map 转为 bean
		Teacher t = BeanUtil.mapToBean(map1, Teacher.class, false, null);
		System.out.println(t);
	}

}
