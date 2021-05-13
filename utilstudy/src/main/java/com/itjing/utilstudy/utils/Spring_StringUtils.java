package com.itjing.utilstudy.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author: lijing
 * @Date: 2021/04/29 13:44
 * @Description: StringUtils工具类常用方法
 */
@Slf4j
public class Spring_StringUtils {
    public static void main(String[] args) {


        // 判断类
        System.out.println("--------------判断类-------------");
        System.out.println("-------------字符串是否为空或者空字符串：\"\"--------------");
        // 字符串是否为空或者空字符串：""
        System.out.println(StringUtils.isEmpty("")); // true
        System.out.println(StringUtils.isEmpty(null)); // true

        System.out.println("-------------字符串是否为空，或者长度为0--------------");

        // 字符串是否为空，或者长度为0
        System.out.println(StringUtils.hasLength(null)); // false
        System.out.println(StringUtils.hasLength("")); // false
        System.out.println(StringUtils.hasLength(" ")); // true
        System.out.println(StringUtils.hasLength("Hello")); // true

        System.out.println("--------------字符串是否有内容（不为空，且不全为空格）-------------");

        // 字符串是否有内容（不为空，且不全为空格）
        System.out.println(StringUtils.hasText(null)); // false
        System.out.println(StringUtils.hasText("")); // false
        System.out.println(StringUtils.hasText(" ")); // false
        System.out.println(StringUtils.hasText("12345")); // true
        System.out.println(StringUtils.hasText(" 12345 ")); // true

        System.out.println("-------------字符串是否包含空格--------------");

        // 字符串是否包含空格
        System.out.println(StringUtils.containsWhitespace(null)); // false
        System.out.println(StringUtils.containsWhitespace("")); // false
        System.out.println(StringUtils.containsWhitespace("a")); // false
        System.out.println(StringUtils.containsWhitespace("abc")); // false
        System.out.println(StringUtils.containsWhitespace(" ")); // true
        System.out.println(StringUtils.containsWhitespace(" a")); // true
        System.out.println(StringUtils.containsWhitespace("abc ")); // true
        System.out.println(StringUtils.containsWhitespace("a b")); // true
        System.out.println(StringUtils.containsWhitespace("a  b")); // true


        System.out.println("-------------字符创头尾操作--------------");

        // 字符创头尾操作

        // 去掉字符串前后的空格
        System.out.println(StringUtils.trimWhitespace(null)); // null
        System.out.println(StringUtils.trimWhitespace("")); // ""
        System.out.println(StringUtils.trimWhitespace(" ")); // ""
        System.out.println(StringUtils.trimWhitespace("\t")); // ""
        System.out.println(StringUtils.trimWhitespace(" a")); // "a"
        System.out.println(StringUtils.trimWhitespace("a ")); // "a"
        System.out.println(StringUtils.trimWhitespace(" a ")); // "a"
        System.out.println(StringUtils.trimWhitespace(" a b ")); // "a b"
        System.out.println(StringUtils.trimWhitespace("   a b   c   ")); // "a b   c"

        // String trimAllWhitespace(String str) 去掉字符串所有的空格
        // String trimLeadingWhitespace(String str) 去掉字符串开头的空格
        // String trimTrailingWhitespace(String str) 去掉字符串结束的空格
        // String trimLeadingCharacter(String str, char leadingCharacter) 去掉字符串开头的指定字符
        // String trimTrailingCharacter(String str, char trailingCharacter) 去掉字符串结尾的指定字符
        // boolean startsWithIgnoreCase(String str, String prefix) 判断字符串是否以指定字符串开头，忽略大小写
        // boolean endsWithIgnoreCase(String str, String suffix) 判断字符串是否以指定字符串结尾，忽略大小写


        System.out.println("-------------文件路径名称相关操作--------------");

        // String unqualify(String qualifiedName):
        // 得到以.分割的最后一个值，可以非常方便的获取类似类名或者文件后缀
        System.out.println(StringUtils.unqualify("com.itjing.java")); // java
        System.out.println(StringUtils.unqualify("com/itjing/Hello.java")); //java

        // String unqualify(String qualifiedName, char separator)
        // 得到以给定字符分割的最后一个值，可以非常方便的获取类似文件名
        System.out.println(StringUtils
                .unqualify("com/itjing/Hello.java", '/')); // Hello.java

        // String capitalize(String str) 首字母大写
        System.out.println(StringUtils.capitalize("itjing")); // Itjing

        // String uncapitalize(String str) 取消首字母大写
        System.out.println(StringUtils.uncapitalize("Itjing")); // itjing

        // String getFilename(String path):获取文件名,就不需要再使用FilenameUtils
        System.out.println(StringUtils.getFilename("mypath/myfile.txt")); // myfile.txt

        // String getFilenameExtension(String path):获取文件后缀名
        System.out.println(StringUtils.getFilenameExtension("mypath/myfile.txt")); // txt

        // String stripFilenameExtension(String path):截取掉文件路径后缀
        System.out.println(StringUtils.stripFilenameExtension("mypath/myfile.txt")); // mypath/myfile


        // String applyRelativePath(String path, String relativePath):
        // 找到给定的文件，和另一个相对路径的文件，返回第二个文件的全路径
        // 打印：d:/java/itjing/other/Some.java
        System.out.println(StringUtils.applyRelativePath(
                "d:/java/itjing/Test.java", "other/Some.java"));

        // 但是不支持重新定位绝对路径和上级目录等复杂一些的相对路径写法：
        // 仍然打印：d:/java/itjing/../other/Some.java
        System.out.println(StringUtils.applyRelativePath(
                "d:/java/itjing/Test.java", "../other/Some.java"));

        // String cleanPath(String path):
        // 清理文件路径,这个方法配合applyRelativePath就可以计算一些简单的相对路径了
        // 打印:d:/java/other/Some.java
        System.out.println(
                StringUtils.cleanPath("d:/java/itjing/../other/Some.java"));

        // 需求：获取d:/java/itjing/Test.java相对路径为../../other/Some.java的文件全路径：
        // 打印：d:/other/Some.java
        System.out.println(StringUtils.cleanPath(StringUtils.applyRelativePath(
                "d:/java/itjing/Test.java", "../../other/Some.java")));

        // boolean pathEquals(String path1, String path2):
        // 判断两个文件路径是否相同，会先执行cleanPath之后再比较
        System.out.println(StringUtils.pathEquals("d:/itjing.txt", "d:/somefile/../itjing.txt"));

        System.out.println("-------------等等等等--------------");

    }
}
