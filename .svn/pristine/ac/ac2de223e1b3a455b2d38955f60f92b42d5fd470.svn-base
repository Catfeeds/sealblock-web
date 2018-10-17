package com.woodare.template.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luke
 */
public class EnumHelper {

	/**
	 * @param key
	 * @return
	 */
	public static List<CodeAndName> getByKey(String key) {
		return codeNamesMap.get(key);
	}

	/**
	 * @param key
	 * @return
	 */
	public static Map<String, String> getMapByKey(String key) {
		return code2NameMap.get(key);
	}

	private static Map<String, List<CodeAndName>> codeNamesMap = new HashMap<String, List<CodeAndName>>();
	private static Map<String, Map<String, String>> code2NameMap = new HashMap<String, Map<String, String>>();

	static {
		// // 二维码类型
		// List<CodeAndName> items = new ArrayList<CodeAndName>();
		// Map<String, String> code2Name = new HashMap<String, String>();
		// for (EnumQrcodeType type : EnumQrcodeType.values()) {
		// items.add(new CodeAndName(type.toString(), type.getDesc()));
		// code2Name.put(type.toString(), type.getDesc());
		// }
		// codeNamesMap.put(EnumQrcodeType.class.getSimpleName(), items);
		// code2NameMap.put(EnumQrcodeType.class.getSimpleName(), code2Name);
	}

	public static class CodeAndName {
		private String code;
		private String name;

		public CodeAndName(String code, String name) {
			this.code = code;
			this.name = name;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		/**
		 * @param code
		 *            the code to set
		 */
		public void setCode(String code) {
			this.code = code;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
	}
}
