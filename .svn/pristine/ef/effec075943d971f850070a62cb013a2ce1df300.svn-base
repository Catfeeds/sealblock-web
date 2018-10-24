package com.woodare.sealblock.rpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.msgpack.jackson.dataformat.MessagePackFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Luke
 */
public class Command {
	private ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());

	private Map<String, Object> headerMap = new HashMap<String, Object>();
	private String methodName;
	private List<?> args;

	/**
	 * @param header
	 * @param methodName
	 * @param args
	 */
	public Command(String methodName, List<?> args) {
		String uid = UUID.randomUUID().toString().replaceAll("-", "");
		headerMap.put("v", 3);
		headerMap.put("message_id", uid);
		headerMap.put("response_to", uid);

		this.methodName = methodName;
		this.args = args;
	}

	/**
	 * @return
	 * @throws JsonProcessingException
	 */
	public byte[] toByteArray() throws JsonProcessingException {
		List<Object> list = new ArrayList<>();
		list.add(headerMap);
		list.add(methodName);
		list.add(args);
		// System.out.println(String.format("REQ_Q[%s]%s", methodName, JSON.toJSONString(args)));
		return objectMapper.writeValueAsBytes(list);
	}

	/**
	 * @return the headerMap
	 */
	public Map<String, Object> getHeaderMap() {
		return headerMap;
	}

	/**
	 * @param headerMap
	 *            the headerMap to set
	 */
	public void setHeaderMap(Map<String, Object> headerMap) {
		this.headerMap = headerMap;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the args
	 */
	public List<?> getArgs() {
		return args;
	}

	/**
	 * @param args
	 *            the args to set
	 */
	public void setArgs(List<?> args) {
		this.args = args;
	}
}
