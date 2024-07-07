package com.itjing.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author lijing
 * @date 2022年06月01日 17:20
 * @description Redis工具类
 */
public class RedisUtils {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/* RedisTemplate操作string类型数据 */
	/**
	 * @description string设置 key和 value的值
	 * @author lijing
	 * @param key
	 * @param value
	 * @return void
	 */
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * @description string设置 key和 value的值并设置过期时间和时间单位
	 * @author lijing
	 * @param key
	 * @param value
	 * @param seconds
	 * @param timeUnit
	 * @return void
	 */
	public void setWithExpire(String key, Object value, Long seconds, TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(key, value, seconds, timeUnit);
	}

	/**
	 * @description string获取 key对应的 value值
	 * @author lijing
	 * @param * @param key
	 * @return java.lang.Object
	 */
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * @description 判断在 redis中是不是存在对应的 key值，有的话就返回 true，没有就返回 false
	 * @author lijing
	 * @param * @param key
	 * @return boolean
	 */
	public Boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * @description 删除redis中对应的key值
	 * @author lijing
	 * @param key
	 * @return boolean
	 */
	public Boolean del(String key) {
		return redisTemplate.delete(key);
	}

	/**
	 * @description 批量删除 redis中对应的 key值，其中 keys是数组 keys:Collection<K> keys
	 * @author lijing
	 * @param keys
	 * @return long
	 */
	public Long batchDel(Collection<String> keys) {
		return redisTemplate.delete(keys);
	}

	/**
	 * @description 把 key值序列化成 byte[]类型
	 * @author lijing
	 * @param key
	 * @return byte[]
	 */
	public byte[] dump(String key) {
		return redisTemplate.dump(key);
	}

	/**
	 * @description 对传入的 key值设置过期时间
	 * @author lijing
	 * @param key
	 * @param seconds
	 * @return void
	 */
	public Boolean expire(String key, long seconds) {
		return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}

	/**
	 * @description 对传入的 key值设置过期日期
	 * @author lijing
	 * @param key
	 * @param date
	 * @return boolean
	 */
	public Boolean expireAt(String key, Date date) {
		return redisTemplate.expireAt(key, date);
	}

	/**
	 * @description 模糊查询，返回一个没有重复的 Set类型
	 * @author lijing
	 * @param key
	 * @return java.util.Set<java.lang.String>
	 */
	public Set<String> getStringKeys(String key) {
		return redisTemplate.keys(key);
	}

	/**
	 * @description 根据新的 key的名称修改 redis中老的 key的名称
	 * @author lijing
	 * @param oldKey
	 * @param newKey
	 * @return void
	 */
	public void rename(String oldKey, String newKey) {
		redisTemplate.rename(oldKey, newKey);
	}

	/**
	 * @description 重命名旧的 key值
	 * @author lijing
	 * @param oldKey
	 * @param newKey
	 * @return boolean
	 */
	public Boolean renameIfAbsent(String oldKey, String newKey) {
		return redisTemplate.renameIfAbsent(oldKey, newKey);
	}

	/**
	 * @description 获取 key值的类型
	 * @author lijing
	 * @param key
	 * @return org.springframework.data.redis.connection.DataType
	 */
	public DataType type(String key) {
		return redisTemplate.type(key);
	}

	/**
	 * @description 随机从 redis中获取一个 key
	 * @author lijing
	 * @param
	 * @return java.lang.String
	 */
	public String randomKey() {
		return redisTemplate.randomKey();
	}

	/**
	 * @description 获取当前 key的剩下的过期时间
	 * @author lijing
	 * @param key
	 * @return void
	 */
	public Long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}

	/**
	 * @description 获取 key剩余的过期时间，同时设置时间单位
	 * @author lijing
	 * @param key
	 * @param timeUnit
	 * @return java.lang.Long
	 */
	public Long getExpire(String key, TimeUnit timeUnit) {
		return redisTemplate.getExpire(key, timeUnit);
	}

	/**
	 * @description 将 key进行持久化
	 * @author lijing
	 * @param key
	 * @return java.lang.Boolean
	 */
	public Boolean persist(String key) {
		return redisTemplate.persist(key);
	}

	/**
	 * @description 将当前数据库的 key移动到指定 redis中数据库当中
	 * @author li
	 * @param key
	 * @param dbIndex
	 * @return java.lang.Boolean
	 */
	public Boolean move(String key, int dbIndex) {
		return redisTemplate.move(key, dbIndex);
	}

	/**
	 * @description 截取 key的子字符串
	 * @author lijing
	 * @param key
	 * @param start
	 * @param end
	 * @return java.lang.String
	 */
	public String subString(String key, long start, long end) {
		return redisTemplate.opsForValue().get(key, start, end);
	}

	/**
	 * @description 设置 key跟 value的值，同时获取 key的值
	 * @author lijing
	 * @param key
	 * @param value
	 * @return java.lang.Object
	 */
	public Object getAndSet(String key, Object value) {
		return redisTemplate.opsForValue().getAndSet(key, value);
	}

	/**
	 * @description 设置多个 key跟 value的值，同时获取 key的值
	 * @author lijing
	 * @param keys
	 * @return java.util.List<java.lang.Object>
	 */
	public List<Object> multiGetSet(List<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	/**
	 * @description 获取原来的 key的值后在后面新增上新的字符串
	 * @author lijing
	 * @param key
	 * @param value
	 * @return java.lang.Integer
	 */
	public Integer append(String key, String value) {
		return redisTemplate.opsForValue().append(key, value);
	}

	/**
	 * @description value 值 +1
	 * @author lijing
	 * @param * @param key
	 * @return java.lang.Long
	 */
	public Long increment(String key) {
		return redisTemplate.opsForValue().increment(key);
	}

	/**
	 * @description 增量方式增加或减少 long值
	 * @author lijing
	 * @param key
	 * @param increment
	 * @return java.lang.Long
	 */
	public Long incrementLong(String key, long increment) {
		return redisTemplate.opsForValue().increment(key, increment);
	}

	/**
	 * @description 增量方式增加double值
	 * @author lijing
	 * @param key
	 * @param increment
	 * @return void
	 */
	public void incrementDouble(String key, double increment) {
		redisTemplate.opsForValue().increment(key, increment);
	}

	/**
	 * @description 不存在即新增map的操作
	 * @author lijing
	 * @param map
	 * @return java.lang.Boolean
	 */
	public Boolean multiSetIfAbsent(Map<? extends String, ?> map) {
		return redisTemplate.opsForValue().multiSetIfAbsent(map);
	}

	/**
	 * @description 保存 map集合
	 * @author lijing
	 * @param map
	 * @return void
	 */
	public void multiSet(Map<String, String> map) {
		redisTemplate.opsForValue().multiSet(map);
	}

	/**
	 * @description 获取 map集合
	 * @author lijing
	 * @param keys
	 * @return java.util.List<java.lang.Object>
	 */
	public List<Object> multiGet(List<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	/**
	 * @description 获取指定 key的字符串的长度
	 * @author lijing
	 * @param key
	 * @return java.lang.Long
	 */
	public Long sizeString(String key) {
		return redisTemplate.opsForValue().size(key);
	}

	/**
	 * @description 根据偏移量 offset 的值，覆盖重写 value的值
	 * @author lijing
	 * @param key
	 * @param value
	 * @param offset
	 * @return void
	 */
	public void offsetValue(String key, Object value, Long offset) {
		redisTemplate.opsForValue().set(key, value, offset);
	}

	/**
	 * @description 对 key所储存的字符串值，获取指定偏移量上的位(bit)
	 * @author lijing
	 * @param key
	 * @param offset
	 * @return java.lang.Boolean
	 */
	public Boolean getOffsetValue(String key, Long offset) {
		return redisTemplate.opsForValue().getBit(key, offset);
	}

	/**
	 * @description 重新设置 key对应的值，如果存在返回 false，否则返回 true
	 * @author lijing
	 * @param key
	 * @param value
	 * @return java.lang.Boolean
	 */
	public Boolean setIfAbsent(String key, Object value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}

	/* RedisTemplate操作hash类型数据 */
	/**
	 * @description 新增map值
	 * @author lijing
	 * @param key
	 * @param field
	 * @param value
	 * @return void
	 */
	public void put(String key, Object field, Object value) {
		redisTemplate.opsForHash().put(key, field, value);
	}

	/**
	 * @description 以map集合的形式添加键值对
	 * @author lijing
	 * @param key
	 * @param map
	 * @return void
	 */
	public void putAll(String key, Map<Object, Object> map) {
		redisTemplate.opsForHash().putAll(key, map);
	}

	/**
	 * @description 获取 map中指定的 key值，如果存在则返回值，没有就返回null
	 * @author lijing
	 * @param key
	 * @param field
	 * @return java.lang.Object
	 */
	public Object getMapValue(String key, String field) {
		return redisTemplate.opsForHash().get(key, field);
	}

	/**
	 * @description 根据 key获取 Map对象
	 * @author lijing
	 * @param key
	 * @return java.util.Map<java.lang.Object,java.lang.Object>
	 */
	public Map<Object, Object> getMap(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * @description 当 hashKey不存在的时候，进行设置 map的值
	 * @author lijing
	 * @param key
	 * @param hashKey
	 * @param value
	 * @return java.lang.Boolean
	 */
	public Boolean putIfAbsent(String key, Object hashKey, Object value) {
		return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
	}

	/**
	 * @description 删除多个map的字段
	 * @author lijing
	 * @param key
	 * @param fields
	 * @return java.lang.Long
	 */
	public Long del(String key, List<Object> fields) {
		return redisTemplate.opsForHash().delete(key, fields);
	}

	/**
	 * @description 查看 hash表中指定字段是否存在
	 * @author lijing
	 * @param key
	 * @param field
	 * @return java.lang.Boolean
	 */
	public Boolean hasKey(String key, Object field) {
		return redisTemplate.opsForHash().hasKey(key, field);
	}

	/**
	 * @description 给 map中指定字段的整数值加上 long型增量 increment
	 * @author lijing
	 * @param key
	 * @param field
	 * @param increment
	 * @return java.lang.Long
	 */
	public Long incrementLong(String key, Object field, long increment) {
		return redisTemplate.opsForHash().increment(key, field, increment);
	}

	/**
	 * @description 给 map中指定字段的整数值加上 double型增量 increment
	 * @author lijing
	 * @param key
	 * @param field
	 * @param increment
	 * @return java.lang.Double
	 */
	public Double incrementDouble(String key, Object field, double increment) {
		return redisTemplate.opsForHash().increment(key, field, increment);
	}

	/**
	 * @description 获取 map中的所有字段
	 * @author lijing
	 * @param key
	 * @return java.util.Set<java.lang.Object>
	 */
	public Set<Object> keys(String key) {
		return redisTemplate.opsForHash().keys(key);
	}

	/**
	 * @description 获取 map中所有字段的数量
	 * @author lijing
	 * @param key
	 * @return java.util.Set<java.lang.Object>
	 */
	public Long sizeHash(String key) {
		return redisTemplate.opsForHash().size(key);
	}

	/**
	 * @description 获取hash表中存在的所有的值
	 * @author lijing
	 * @param key
	 * @return java.util.List<java.lang.Object>
	 */
	public List<Object> values(String key) {
		return redisTemplate.opsForHash().values(key);
	}

	/**
	 * @description 查看匹配的键值对
	 * @author lijing
	 * @param key
	 * @param scanOptions
	 * @return org.springframework.data.redis.core.Cursor<java.util.Map.Entry<java.lang.Object,java.lang.Object>>
	 */
	public Cursor<Map.Entry<Object, Object>> scan(String key, ScanOptions scanOptions) {
		return redisTemplate.opsForHash().scan(key, scanOptions);
	}

	/* RedisTemplate操作list类型数据 */
	/**
	 * @description 把值添加在 list的最前面
	 * @author lijing
	 * @param key
	 * @param value
	 * @return java.lang.Long
	 */
	public Long leftPush(String key, Object value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * @description 把值添加在 list
	 * @author lijing
	 * @param key
	 * @param values
	 * @return java.lang.Long
	 */
	public Long leftPush(String key, Object... values) {
		return redisTemplate.opsForList().leftPushAll(key, values);
	}

	/**
	 * @description 直接把一个新的 list添加到老的 list上面去
	 * @author lijing
	 * @param key
	 * @param value
	 * @return java.lang.Long
	 */
	public Long leftPushAll(String key, List<Object> value) {
		return redisTemplate.opsForList().leftPushAll(key, value);
	}

	/**
	 * @description List存在的时候就加入新的值
	 * @author lijing
	 * @param key
	 * @param value
	 * @return long
	 */
	public long leftPushIfPresent(String key, Object value) {
		return redisTemplate.opsForList().leftPushIfPresent(key, value);
	}

	/**
	 * @description 把值添加在 list的最后面
	 * @author lijing
	 * @param key
	 * @param value
	 * @return java.lang.Long
	 */
	public long rightPush(String key, Object value) {
		return redisTemplate.opsForList().rightPush(key, value);
	}

	/**
	 * @description 把值添加在 list的最后面
	 * @author lijing
	 * @param key
	 * @param values
	 * @return long
	 */
	public long rightPushAll(String key, Object... values) {
		return redisTemplate.opsForList().rightPushAll(key, values);
	}

	/**
	 * @description 把值添加在 list的最后面
	 * @author lijing
	 * @param key
	 * @param values
	 * @return long
	 */
	public long rightPushAll(String key, List<Object> values) {
		return redisTemplate.opsForList().rightPushAll(key, values);
	}

	/**
	 * @description 根据索引获取 list中的值
	 * @author lijing
	 * @param key
	 * @param index
	 * @return java.lang.Object
	 */
	public Object index(String key, long index) {
		return redisTemplate.opsForList().index(key, index);
	}

	/**
	 * @description 获取 list中开始索引到结束索引的所有值
	 * @author lijing
	 * @param key
	 * @param start
	 * @param end
	 * @return java.util.List<java.lang.Object>
	 */
	public List<Object> range(String key, long start, long end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	/**
	 * @description 移除并获取列表中第一个元素
	 * @author lijing
	 * @param key
	 * @return java.lang.Object
	 */
	public Object leftPop(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * @description 移除并获取列表中第一个元素
	 * @author lijing
	 * @param key
	 * @param seconds
	 * @param timeUnit
	 * @return java.lang.Object
	 */
	public Object leftPop(String key, Long seconds, TimeUnit timeUnit) {
		return redisTemplate.opsForList().leftPop(key, seconds, timeUnit);
	}

	/**
	 * @description 移除并获取列表中最后一个元素
	 * @author lijing
	 * @param key
	 * @return java.lang.Object
	 */
	public Object rightPop(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	/**
	 * @description 移除并获取列表中最后一个元素
	 * @author lijing
	 * @param key
	 * @param seconds
	 * @param timeUnit
	 * @return java.lang.Object
	 */
	public Object rightPop(String key, Long seconds, TimeUnit timeUnit) {
		return redisTemplate.opsForList().rightPop(key, seconds, timeUnit);
	}

	/**
	 * @description 从一个队列的右边弹出一个元素并将这个元素放入另一个指定队列的最左边
	 * @author lijing
	 * @param sourceKey
	 * @param destinationKey
	 * @return java.lang.Object
	 */
	public Object rightPopAndLeftPush(String sourceKey, String destinationKey) {
		return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
	}

	/**
	 * @description 从一个队列的右边弹出一个元素并将这个元素放入另一个指定队列的最左边
	 * @author lijing
	 * @param sourceKey
	 * @param destinationKey
	 * @param seconds
	 * @param timeUnit
	 * @return java.lang.Object
	 */
	public Object rightPopAndLeftPush(String sourceKey, String destinationKey, long seconds, TimeUnit timeUnit) {
		return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey, seconds, timeUnit);
	}

	/**
	 * @description 获取 list的大小
	 * @author lijing
	 * @param key
	 * @return java.lang.Long
	 */
	public Long sizeList(String key) {
		return redisTemplate.opsForList().size(key);
	}

	/**
	 * @description 剪裁 List列表
	 * @author lijing
	 * @param key
	 * @param start
	 * @param end
	 * @return void
	 */
	public void trim(String key, long start, long end) {
		redisTemplate.opsForList().trim(key, start, end);
	}

	/**
	 * @description 删除集合中值等于value的元素
	 * @author lijing
	 * @param key
	 * @param index
	 * @param value
	 * @return java.lang.Long
	 */
	public Long remove(String key, long index, Object value) {
		return redisTemplate.opsForList().remove(key, index, value);
	}

	/* RedisTemplate操作set类型数据 */
	/**
	 * @description 添加元素到 set中
	 * @author lijing
	 * @param key
	 * @param values
	 * @return java.lang.Long
	 */
	public Long add(String key, Collection<Object> values) {
		return redisTemplate.opsForSet().add(key, values);
	}

	/**
	 * @description 从 set中删除一个随机元素，并返回该元素
	 * @author lijing
	 * @param key
	 * @return java.lang.Object
	 */
	public Object pop(String key) {
		return redisTemplate.opsForSet().pop(key);
	}

	/**
	 * @description 获取 set集合的大小
	 * @author lijing
	 * @param key
	 * @return long
	 */
	public Long sizeSet(String key) {
		return redisTemplate.opsForSet().size(key);
	}

	/**
	 * @description 判断 set集合中是否存在value值
	 * @author lijing
	 * @param key
	 * @param value
	 * @return java.lang.Boolean
	 */
	public Boolean isMember(String key, Object value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}

	/**
	 * @description 获取两个集合的交集并返回一个集合
	 * @author lijing
	 * @param key
	 * @param otherKey
	 * @return java.util.Set<java.lang.Object>
	 */
	public Set<Object> intersect(String key, String otherKey) {
		return redisTemplate.opsForSet().intersect(key, otherKey);
	}

	/**
	 * @description 获取两个集合的交集并返回一个集合
	 * @author lijing
	 * @param key
	 * @param collection
	 * @return java.util.Set<java.lang.Object>
	 */
	public Set<Object> intersect(String key, Collection<String> collection) {
		return redisTemplate.opsForSet().intersect(key, collection);
	}

	/**
	 * @description 获取两个集合交集，并存储到 destKey
	 * @author lijing
	 * @param key1
	 * @param key2
	 * @param destKey
	 * @return java.lang.Long
	 */
	public Long intersectAndStore(String key1, String key2, String destKey) {
		return redisTemplate.opsForSet().intersectAndStore(key1, key2, destKey);
	}

	/**
	 * @description 获取两个集合的并集
	 * @author lijing
	 * @param key
	 * @param key1
	 * @return java.util.Set<java.lang.Object>
	 */
	public Set<Object> union(String key, String key1) {
		return redisTemplate.opsForSet().union(key, key1);
	}

	/**
	 * @description 获取两个集合的并集
	 * @author lijing
	 * @param key
	 * @param collection
	 * @return java.util.Set<java.lang.Object>
	 */
	public Set<Object> union(String key, Collection<String> collection) {
		return redisTemplate.opsForSet().union(key, collection);
	}

	/**
	 * @description 获取两个集合的并集，并存储到 destKey
	 * @author lijing
	 * @param key
	 * @param key1
	 * @param destKey
	 * @return java.lang.Long
	 */
	public Long unionAndStore(String key, String key1, String destKey) {
		return redisTemplate.opsForSet().unionAndStore(key, key1, destKey);
	}

	/**
	 * @description 获取两个集合的差集
	 * @author lijing
	 * @param key
	 * @param key1
	 * @return java.util.Set<java.lang.Object>
	 */
	public Set<Object> difference(String key, String key1) {
		return redisTemplate.opsForSet().difference(key, key1);
	}

	/**
	 * @description 获取两个集合的差集
	 * @author lijing
	 * @param key
	 * @param collection
	 * @return java.util.Set<java.lang.Object>
	 */
	public Set<Object> difference(String key, Collection<String> collection) {
		return redisTemplate.opsForSet().difference(key, collection);
	}

	/**
	 * @description 获取两个集合的差集，并存储到 destKey
	 * @author lijing
	 * @param key
	 * @param key1
	 * @param destKey
	 * @return java.lang.Long
	 */
	public Long differenceAndStore(String key, String key1, String destKey) {
		return redisTemplate.opsForSet().differenceAndStore(key, key1, destKey);
	}

	/**
	 * @description 获取集合中的所有元素
	 * @author lijing
	 * @param key
	 * @return java.util.Set<java.lang.Object>
	 */
	public Set<Object> members(String key) {
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * @description 随机获取集合中一个元素
	 * @author lijing
	 * @param key
	 * @return java.lang.Object
	 */
	public Object randomMember(String key) {
		return redisTemplate.opsForSet().randomMember(key);
	}

	/**
	 * @description 随机获取集合中 count个元素，返回一个 List集合
	 * @author lijing
	 * @param key
	 * @param count
	 * @return java.util.List<java.lang.Object>
	 */
	public List<Object> randomMembers(String key, long count) {
		return redisTemplate.opsForSet().randomMembers(key, count);
	}

	/**
	 * @description 随机获取集合中 count个元素，去重后返回一个 Set集合
	 * @author lijing
	 * @param key
	 * @param count
	 * @return java.util.Set<java.lang.Object>
	 */
	public Set<Object> distinctRandomMembers(String key, long count) {
		return redisTemplate.opsForSet().distinctRandomMembers(key, count);
	}

	/**
	 * @description 遍历 set
	 * @author lijing
	 * @param key
	 * @param scanOptions
	 * @return org.springframework.data.redis.core.Cursor<java.lang.Object>
	 */
	public Cursor<Object> scanSet(String key, ScanOptions scanOptions) {
		return redisTemplate.opsForSet().scan(key, scanOptions);
	}

	/**
	 * @description 移除元素
	 * @author lijing
	 * @param key
	 * @param objects
	 * @return java.lang.Long
	 */
	public Long remove(String key, Collection<Object> objects) {
		return redisTemplate.opsForSet().remove(key, objects);
	}

	/* RedisTemplate操作zset类型数据 */
	/**
	 * @description 添加元素到 zset，从小到大排序
	 * @author lijing
	 * @param key
	 * @param value
	 * @param score
	 * @return java.lang.Boolean
	 */
	public Boolean add(String key, Object value, double score) {
		return redisTemplate.opsForZSet().add(key, value, score);
	}

	/**
	 * @description 增加元素的 score值同时返回增加后的 score值
	 * @author lijing
	 * @param key
	 * @param value
	 * @param score
	 * @return java.lang.Double
	 */
	public Double incrementScore(String key, Object value, double score) {
		return redisTemplate.opsForZSet().incrementScore(key, value, score);
	}

	/**
	 * @description 返回 zset元素在集合的从小到大排名
	 * @author lijing
	 * @param key
	 * @param object
	 * @return java.lang.Long
	 */
	public Long rank(String key, Object object) {
		return redisTemplate.opsForZSet().rank(key, object);
	}

	/**
	 * @description 返回 zset元素在集合的由大到小排名
	 * @author lijing
	 * @param key
	 * @param object
	 * @return java.lang.Long
	 */
	public Long reverseRank(String key, Object object) {
		return redisTemplate.opsForZSet().reverseRank(key, object);
	}

	/**
	 * @description 获取 zset集合中指定区间的元素
	 * @author lijing
	 * @param key
	 * @param start
	 * @param end
	 * @return java.util.Set<org.springframework.data.redis.core.ZSetOperations.TypedTuple<java.lang.Object>>
	 */
	public Set<ZSetOperations.TypedTuple<Object>> reverseRangeWithScores(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
	}

	/**
	 * @description 查询 zset集合中的元素并从小到大排序
	 * @author lijing
	 * @param key
	 * @param min
	 * @param max
	 * @return java.util.Set<java.lang.Object>
	 */
	public Set<Object> reverseRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
	}

	/**
	 * @description 从高到低进行排序，然后获取最小与最大值之间的值
	 * @author lijing
	 * @param key
	 * @param min
	 * @param max
	 * @param start
	 * @param end
	 * @return java.util.Set<java.lang.Object>
	 */
	public Set<Object> reverseRangeByScore(String key, double min, double max, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end);
	}

	/**
	 * @description 查询 zset集合中的元素并从小到大排序
	 * @author lijing
	 * @param key
	 * @param min
	 * @param max
	 * @return java.util.Set<org.springframework.data.redis.core.ZSetOperations.TypedTuple<java.lang.Object>>
	 */
	public Set<ZSetOperations.TypedTuple<Object>> reverseRangeByScoreWithScores(String key, double min, double max) {
		return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
	}

	/**
	 * @description 根据score值获取元素数量
	 * @author lijing
	 * @param key
	 * @param min
	 * @param max
	 * @return java.lang.Long
	 */
	public Long count(String key, double min, double max) {
		return redisTemplate.opsForZSet().count(key, min, max);
	}

	/**
	 * @description 获取 zset集合的大小
	 * @author lijing
	 * @param key
	 * @return java.lang.Long
	 */
	public Long sizeZset(String key) {
		return redisTemplate.opsForZSet().size(key);
	}

	/**
	 * @description 获取 zset集合的大小
	 * @author lijing
	 * @param key
	 * @return java.lang.Long
	 */
	public Long zCard(String key) {
		return redisTemplate.opsForZSet().zCard(key);
	}

	/**
	 * @description 获取集合中 key、value元素的 score值
	 * @author lijing
	 * @param key
	 * @param value
	 * @return java.lang.Double
	 */
	public Double score(String key, Object value) {
		return redisTemplate.opsForZSet().score(key, value);
	}

	/**
	 * @description 移除 zset中指定索引元素
	 * @author lijing
	 * @param key
	 * @param start
	 * @param end
	 * @return java.lang.Long
	 */
	public Long removeRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().removeRange(key, start, end);
	}

	/**
	 * @description 移除 zset中指定 score范围的集合成员
	 * @author lijing
	 * @param key
	 * @param min
	 * @param max
	 * @return java.lang.Long
	 */
	public Long removeRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
	}

	/**
	 * @description 获取 key和 key1的并集并存储在 destKey中
	 * @author lijing
	 * @param key
	 * @param key1
	 * @param destKey
	 * @return java.lang.Long
	 */
	public Long zSetUnionAndStore(String key, String key1, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(key, key1, destKey);
	}

	/**
	 * @description 获取 key和 collection集合的并集并存储在 destKey中
	 * @author lijing
	 * @param key
	 * @param collection
	 * @param destKey
	 * @return java.lang.Long
	 */
	public Long zSetUnionAndStore(String key, Collection<String> collection, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(key, collection, destKey);
	}

	/**
	 * @description 获取 key和 key1的交集并存储在 destKey中
	 * @author lijing
	 * @param key
	 * @param key1
	 * @param destKey
	 * @return java.lang.Long
	 */
	public Long zSetIntersectAndStore(String key, String key1, String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, key1, destKey);
	}

	/**
	 * @description 获取 key和 collection集合的交集并存储在 destKey中
	 * @author lijing
	 * @param key
	 * @param collection
	 * @param destKey
	 * @return java.lang.Long
	 */
	public Long zSetIntersectAndStore(String key, Collection<String> collection, String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, collection, destKey);
	}

	/**
	 * @description 删除多个values的值
	 * @author lijing
	 * @param key
	 * @param values
	 * @return java.lang.Long
	 */
	public Long remove(String key, Object... values) {
		return redisTemplate.opsForZSet().remove(key, values);
	}

}
