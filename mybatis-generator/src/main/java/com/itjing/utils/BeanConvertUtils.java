package com.itjing.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author lijing
 * @date 2022年05月31日 17:07
 * @description 转换对象工具类
 */
public class BeanConvertUtils extends BeanUtils {

	public static <S, T> T convertTo(S source, Supplier<T> targetSupplier) {
		return convertTo(source, targetSupplier, null);
	}

	/**
	 * 转换对象
	 * @param source 源对象
	 * @param targetSupplier 目标对象供应方
	 * @param callBack 回调方法
	 * @param <S> 源对象类型
	 * @param <T> 目标对象类型
	 * @return 目标对象
	 */
	public static <S, T> T convertTo(S source, Supplier<T> targetSupplier, ConvertCallBack<S, T> callBack) {
		if (null == source || null == targetSupplier) {
			return null;
		}

		T target = targetSupplier.get();
		copyProperties(source, target);
		if (callBack != null) {
			callBack.callBack(source, target);
		}
		return target;
	}

	public static <S, T> List<T> convertListTo(List<S> sources, Supplier<T> targetSupplier) {
		return convertListTo(sources, targetSupplier, null);
	}

	/**
	 * 转换对象
	 * @param sources 源对象list
	 * @param targetSupplier 目标对象供应方
	 * @param callBack 回调方法
	 * @param <S> 源对象类型
	 * @param <T> 目标对象类型
	 * @return 目标对象list
	 */
	public static <S, T> List<T> convertListTo(List<S> sources, Supplier<T> targetSupplier,
			ConvertCallBack<S, T> callBack) {
		if (null == sources || null == targetSupplier) {
			return null;
		}

		List<T> list = new ArrayList<>(sources.size());
		for (S source : sources) {
			T target = targetSupplier.get();
			copyProperties(source, target);
			if (callBack != null) {
				callBack.callBack(source, target);
			}
			list.add(target);
		}
		return list;
	}

	/**
	 * 回调接口
	 *
	 * @param <S> 源对象类型
	 * @param <T> 目标对象类型
	 */
	@FunctionalInterface
	public interface ConvertCallBack<S, T> {

		void callBack(S t, T s);

	}

	public static void main(String[] args) {
		/**
		 * 当要转换po、vo时，只需要 // 使用前 public Vo findById(Integer id) { Vo vo = new Vo(); Po po
		 * = dao.findById(id); copyProperties(po, vo); return vo; }
		 *
		 * // 使用后 public Vo findById(Integer id) { return
		 * BeanConvertUtils.converTo(dao.findById(id), Vo::new); }
		 *
		 * // 使用后，通过lambda表达式特殊处理个别字段 public Vo findById(Integer id) { return
		 * BeanConvertUtils.converTo(dao.findById(id), Vo::new, (s, t) ->
		 * t.setName(s.getName)) ); }
		 */

		/**
		 * 当要拷贝list的时候也很简单 // 使用前 public List<Vo> findAll() { List<Vo> vos = new
		 * ArrayList(); List<Po> pos = dao.findAll(); for (Po po : Pos) { Vo vo = new
		 * Vo(); BeanUtis.copyProperties(po, vo); vos.add(vo); } return vos; }
		 *
		 * // 使用后 public List<Vo> findAll() { return
		 * BeanConvertUtils.converToList(dao.findAll(), Vo::new) }
		 *
		 * // 同样支持自定义lambda public List<Vo> findAll() { return
		 * BeanConvertUtils.converToList(dao.findAll(), Vo::new, (s, t) ->
		 * t.setName(s.getName)) ) }
		 */

		/**
		 * 由于只是BeanUtils的一个封装，跟原来的代码性能几乎差不多， 如果要说差一点也没错，毕竟多了一层函数堆栈的调用，
		 * 但是基本可以忽略不计。主要的性能还是由BeanUtils决定。
		 */

		/**
		 * 有两点要提醒： ● 此方法依旧不能解决深层次的深拷贝问题，详细的可以google一下BeanUtils的深拷贝问题 ●
		 * 如果source或者targetSupplier只要有一个为null，本工具类不像BeanUtils一样抛出异常，而是返回null，
		 * 因为笔者认为调用方如果把null进行准换，那就是想转换为null，为不为空应该由调用方自己负责
		 */

	}

}
