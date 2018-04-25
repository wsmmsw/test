package com.baicheng.fork.core.util;

import com.baicheng.fork.domain.BackConstants;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 *
 * @author SongPengpeng
 */
public class XmlUtil {

	/**
	 *
	 * @param object
	 * @return
	 */
	public static String bean2Xml(Object object) {
		XStream xStream = new XStream(new DomDriver(BackConstants.ENCODE_TYPE_UTF_8));
		xStream.processAnnotations(object.getClass());
		return xStream.toXML(object);
	}

	/**
	 *
	 * @param <T>
	 * @param xml
	 * @param cls
	 * @return
	 */
	public static <T> T xml2Bean(String xml, Class<T> cls) {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(cls);
		@SuppressWarnings("unchecked")
		T t = (T) xstream.fromXML(xml);
		return t;
	}

}
