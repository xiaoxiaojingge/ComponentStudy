package com.itjing.springboot.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.itjing.springboot.entity.SchoolBO;
import com.itjing.springboot.entity.StudentBO;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * xml工具类
 *
 * @author lijing
 * @date 2024-06-28
 */
public class XmlUtil {
    private static final XmlMapper XML_MAPPER = createXmlMapper();

    /**
     * 创建一个配置好的XmlMapper实例。
     * 该方法配置了XmlMapper的各种设置，包括序列化和反序列化的行为。
     *
     * @return 配置好的XmlMapper实例。
     */
    private static XmlMapper createXmlMapper() {
        // 创建一个XmlMapper实例，并通过builder模式进行配置。
        XmlMapper xmlMapper = XmlMapper.builder()
                // 忽略实体类没有对应属性。如果为 true 会抛出异常
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                //  忽略null
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                // 属性使用 驼峰首字母小写
                .propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
                .defaultUseWrapper(false)
                .configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true)
                // 配置XmlMapper在序列化XML时包含XML声明。
                .configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true)
                // 配置XmlMapper在序列化时使用缩进，以美化输出。
                .enable(SerializationFeature.INDENT_OUTPUT)
                // 禁止忽略重复的模块注册，以确保后续注册的模块生效。
                .disable(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS)
                .build();
        // 注册自定义的模块，以扩展XmlMapper的功能。
        xmlMapper.registerModule(configTimeModule());
        // 返回配置好的XmlMapper实例。
        return xmlMapper;
    }


    /**
     * 配置Java时间模块，以支持序列化和反序列化Java 8的时间类。
     * 这个方法设置了DateTimeFormatter的模式，用于将LocalDateTime、LocalDate、LocalTime和Date类的对象
     * 序列化为JSON字符串，以及将JSON字符串反序列化为这些时间类的对象。
     *
     * @return 配置好的JavaTimeModule实例。
     */
    private static JavaTimeModule configTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        // 定义日期时间格式，用于序列化和反序列化
        String localDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
        String localDateFormat = "yyyy-MM-dd";
        String localTimeFormat = "HH:mm:ss";
        String dateFormat = "yyyy-MM-dd HH:mm:ss";

        // 添加序列化器，用于将Java 8的时间类序列化为JSON字符串
        // 序列化
        javaTimeModule.addSerializer(
                LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(localDateTimeFormat)));
        javaTimeModule.addSerializer(
                LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern(localDateFormat)));
        javaTimeModule.addSerializer(
                LocalTime.class,
                new LocalTimeSerializer(DateTimeFormatter.ofPattern(localTimeFormat)));
        javaTimeModule.addSerializer(
                Date.class,
                new DateSerializer(false, new SimpleDateFormat(dateFormat)));

        // 添加反序列化器，用于将JSON字符串反序列化为Java 8的时间类对象
        // 反序列化
        javaTimeModule.addDeserializer(
                LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(localDateTimeFormat)));
        javaTimeModule.addDeserializer(
                LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(localDateFormat)));
        javaTimeModule.addDeserializer(
                LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ofPattern(localTimeFormat)));
        javaTimeModule.addDeserializer(Date.class, new DateDeserializers.DateDeserializer() {
            @SneakyThrows
            @Override
            public Date deserialize(JsonParser jsonParser, DeserializationContext dc) {
                String text = jsonParser.getText().trim();
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                return sdf.parse(text);
            }
        });

        return javaTimeModule;
    }

    /**
     * 将给定的对象转换为XML格式的字符串。
     * <p>
     * 使用Jackson库的XMLMapper将Java对象序列化为XML格式的字符串。
     * 此方法封装了序列化过程，并在发生异常时抛出运行时异常。
     *
     * @param o 待转换为XML字符串的Java对象。
     * @return 对象的XML表示形式的字符串。
     * @throws RuntimeException 如果序列化过程中发生错误。
     */
    public static String toXml(Object o) {
        String xml = null;
        try {
            // 使用XML_MAPPER将对象序列化为XML字符串
            xml = XML_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            // 在序列化过程中发生异常时，抛出运行时异常
            throw new RuntimeException(e);
        }
        return xml;
    }

    /**
     * 将XML格式的字符串转换为指定类型的对象。
     * <p>
     * 本方法使用Jackson库的XMLMapper来解析XML字符串，将其映射为Java对象。
     * 这种方式提供了便利的XML到对象的转换，适用于处理XML格式的数据。
     *
     * @param xml 待转换的XML格式的字符串。
     * @param cls 指定转换后的对象类型，使用Class对象来表示。
     * @return 返回转换后的对象实例。
     * @throws RuntimeException 如果XML字符串无法解析为指定类型的对象，则抛出运行时异常。
     */
    public static <T> T toObject(String xml, Class<T> cls) {
        T t = null;
        try {
            // 使用XML_MAPPER的readValue方法将XML字符串转换为指定类型的对象。
            t = XML_MAPPER.readValue(xml, cls);
        } catch (JsonProcessingException e) {
            // 在解析过程中发生错误时，抛出运行时异常。
            throw new RuntimeException(e);
        }
        return t;
    }

    public static void main(String[] args) {
        SchoolBO schoolBO = new SchoolBO();
        schoolBO.setSchoolId(222);
        schoolBO.setSchoolName("ABC中学");
        schoolBO.setSchoolEmail("aa@163.com");
        schoolBO.setSchoolAddress("A省B市");

        List<StudentBO> studentBOList = new ArrayList<>();
        StudentBO student1 = new StudentBO();
        student1.setStudentName("Tony");
        student1.setCreateTime(LocalDateTime.now());

        StudentBO student2 = new StudentBO();
        student2.setStudentName("Peter");
        student2.setCreateTime(LocalDateTime.now().plusSeconds(2));

        studentBOList.add(student1);
        studentBOList.add(student2);

        schoolBO.setStudentList(studentBOList);

        System.out.println(toXml(schoolBO));
    }


}
