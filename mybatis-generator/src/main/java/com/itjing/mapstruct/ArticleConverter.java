package com.itjing.mapstruct;

import com.itjing.entity.Article;
import com.itjing.vo.ArticleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lijing
 * @date 2021年11月23日 16:29
 * @description 若源对象属性与目标对象属性名字一致，会自动映射对应属性，不一样的需要通过注解指定映射
 * 可以参考这篇文章：https://blog.csdn.net/zhige_me/article/details/80699784
 */
@Mapper
public interface ArticleConverter {

	ArticleConverter INSTANCE = Mappers.getMapper(ArticleConverter.class);

	ArticleVO domain2VO(Article article);

	List<ArticleVO> domain2VO(List<Article> articles);

}
