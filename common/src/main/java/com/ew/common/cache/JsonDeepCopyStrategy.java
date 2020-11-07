package com.ew.common.cache;

import java.util.Collection;

import com.alibaba.fastjson.JSON;

import net.sf.ehcache.Element;
import net.sf.ehcache.store.compound.ReadWriteCopyStrategy;

/**
 * Json深拷贝
 * @author Mr`Huang
 * @Date 2020-11-7 14:01:25
 */
public class JsonDeepCopyStrategy implements ReadWriteCopyStrategy<Element> {

	private static final long serialVersionUID = -2096901133553373363L;

	@Override // 当write缓存的时候会调用这个方法
	public Element copyForWrite(Element value, ClassLoader loader) {
		return value;
	}

	@Override // 当read缓存的时候会调用这个方法
	public Element copyForRead(Element storedValue, ClassLoader loader) {
		Object deepCopyByJson = deepCopyByJson(storedValue.getObjectValue());
		return new Element(storedValue.getObjectKey(), deepCopyByJson);
	}

	/**
	 * 使用Json进行java深拷贝
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Object deepCopyByJson(Object obj) {
		if (obj == null) {
			return null;
		}
		String json = JSON.toJSONString(obj);
		if (obj instanceof Collection) {
			Collection c = (Collection)obj;
			if (!c.isEmpty()) {
				Class<? extends Object> cls = c.iterator().next().getClass();
				return JSON.parseArray(json, cls);
			}
		}
		return JSON.parseObject(json, Object.class);
	}

}
