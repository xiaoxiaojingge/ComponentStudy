package com.itjing.pojo;

import cn.hutool.core.annotation.Alias;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

/**
 * 其中，@CsvBindByPosition是OpenCsv的注解，@Alias是Hutool的注解
 */
@Getter
@Setter
public class CsvDTO {

    /**
     * 如果不指定csv文件中的顺序，那么他是基于列名升序排列，
     * 那么这里就需要用@CsvBindByPosition(position = 0)来定位位置，
     * 但是如果你用这个来定位的话，那么表头就展示不出来，如果@CsvBindByName的话，
     * 又定位不了位置，那么这里我的解决方案就是，用@CsvBindByPosition(position = 0)来定位位置，
     * 表头的话再自己写入。
     * 如果列中出现了时间相关的数据，那么他展示的数据是GMT+8这种格式，
     * 这时候的解决方案是用@CsvDate("yyyy-MM-dd HH:mm:ss")来进行时间格式化。
     */

    @Alias("姓名")
    @CsvBindByPosition(position = 0)
    private String name;

    @Alias("年龄")
    @CsvBindByPosition(position = 1)
    private String age;
}