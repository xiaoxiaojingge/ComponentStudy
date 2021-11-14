package com.itjing.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ContentRowHeight(20) // 行高
@HeadRowHeight(20) // 标题高度
@ColumnWidth(20) // 列宽
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER) // 内容居中
@ContentFontStyle(fontHeightInPoints = 16) // 内容字体
public class Article implements Serializable {

    @ExcelIgnore
    private Integer id;

    @ExcelProperty(value = "作者", index = 0)
    private String author;

    @ExcelProperty(value = "标题", index = 1)
    private String title;

    @ColumnWidth(50)
    @ExcelProperty(value = "内容", index = 2)
    private String content;

   /* @DateTimeFormat("yyyy-MM-dd HH:mm:ss") // 格式化日期
    @ExcelProperty(value = "创建时间", index = 3)
    private Date createTime;*/
}