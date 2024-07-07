package com.itjing.util;

import java.util.List;

public interface TreeEntity<E> {

	public Object getId();

	public Object getParentId();

	public void setChildList(List<E> childList);

	public List<E> getChildList();

}
