package com.itjing.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lijing
 * @date 2021年11月13日 8:22
 * @description BaseService接口，利用反射，也可以根据生成的那些基本的操作，做一些封装操作，看自己的需求了
 */

public interface BaseService<Record, Example> {

	/**
	 * 根据条件查询记录数量
	 * @param example
	 * @return
	 */
	int countByExample(Example example);

	/**
	 * 根据条件删除记录
	 * @param example
	 * @return
	 */
	int deleteByExample(Example example);

	/**
	 * 根据主键删除记录
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Object id);

	/**
	 * 插入记录
	 * @param record
	 * @return
	 */
	int insert(Record record);

	/**
	 * 插入记录有效字段
	 * @param record
	 * @return
	 */
	int insertSelective(Record record);

	/**
	 * 根据条件查询记录，附带BLOB字段
	 * @param example
	 * @return
	 */
	List<Record> selectByExampleWithBLOBs(Example example);

	/**
	 * 根据条件查询记录
	 * @param example
	 * @return
	 */
	List<Record> selectByExample(Example example);

	/**
	 * 根据条件查询第一条记录
	 * @param example
	 * @return
	 */
	Record selectFirstByExample(Example example);

	/**
	 * 根据条件查询第一条记录，附带BLOB字段
	 * @param example
	 * @return
	 */
	Record selectFirstByExampleWithBLOBs(Example example);

	/**
	 * 根据主键查询记录
	 * @param id
	 * @return
	 */
	Record selectByPrimaryKey(Object id);

	/**
	 * 根据条件更新有效字段
	 * @param record
	 * @param example
	 * @return
	 */
	int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example);

	/**
	 * 根据条件更新记录有效字段，附带BLOB字段
	 * @param record
	 * @param example
	 * @return
	 */
	int updateByExampleWithBLOBs(@Param("record") Record record, @Param("example") Example example);

	/**
	 * 根据条件更新记录
	 * @param record
	 * @param example
	 * @return
	 */
	int updateByExample(@Param("record") Record record, @Param("example") Example example);

	/**
	 * 根据主键更新记录有效字段
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(Record record);

	/**
	 * 根据主键更新记录，附带BLOB字段
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeyWithBLOBs(Record record);

	/**
	 * 根据主键更新记录
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(Record record);

	/**
	 * 根据主键批量删除记录
	 * @param ids
	 * @return
	 */
	int deleteByPrimaryKeys(String ids, Class<?> clazz);

	/**
	 * 插入还是更新，如果主键存在就是更新
	 * @param record
	 * @return
	 */
	int insertOrUpdate(Record record);

	/**
	 * 插入还是更新，如果主键存在就是更新，并且更新有效字段
	 * @param record
	 * @return
	 */
	int insertOrUpdateSelective(Record record);

	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	int updateBatch(@Param("list") List<Record> list);

	/**
	 * 批量更新，更新有效字段
	 * @param list
	 * @return
	 */
	int updateBatchSelective(@Param("list") List<Record> list);

	/**
	 * 批量新增
	 * @param list
	 * @return
	 */
	int batchInsert(@Param("list") List<Record> list);

	/**
	 * 批量新增，有效字段
	 * @param list
	 * @return
	 */
	int batchInsertSelective(@Param("list") List<Record> list);

	/**
	 * 初始化mapper
	 */
	void initMapper();

}
