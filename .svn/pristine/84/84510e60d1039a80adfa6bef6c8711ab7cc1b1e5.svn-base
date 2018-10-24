package com.woodare.sealblock.util;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.zeromq.ZMQ;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woodare.sealblock.rpc.Command;

/**
 * Sealblock底层链通讯服务客户端
 * 
 * @author Luke
 */
public class SealblockClient {
	private static Logger log = Logger.getLogger(SealblockClient.class);

	public static final int TIMEOUT_SECONDS = 5;
	public static final long TRANSACTION_TIMEOUT = 3L;

	private static final int THREAD_NUM = 100;

	private static ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);

	/**
	 * @param address
	 * @param tagId
	 * @param methodName
	 * @param args
	 * @return
	 * @throws SealblockException
	 */
	public static Object invoke(String address, String tagId, String methodName, List<?> args) throws SealblockException {
		return invoke(address, tagId, methodName, args, TIMEOUT_SECONDS);
	}

	/**
	 * @param address
	 * @param tagId
	 * @param methodName
	 * @param args
	 * @param timeout
	 * @return
	 * @throws SealblockException
	 */
	public static Object invoke(String address, String tagId, String methodName, List<?> args, long timeout) throws SealblockException {
		long startTime = new Date().getTime();

		Object result = null;
		try {
			Command command = new Command(methodName, args);
			log.info(methodName + "Reqe[" + tagId + "]" + JSON.toJSONString(command));

			byte[] commandData = null;
			try {
				commandData = command.toByteArray();
			}
			// 请求数据异常
			catch (JsonProcessingException e) {
				throw new SealblockRequestException(e.getMessage(), e);
			}

			Object items = postRemote(commandData, address, timeout);
			log.info(methodName + "Resp[" + tagId + "](" + (new Date().getTime() - startTime) + ")" + JSON.toJSONString(items));

			result = processResult(items);
		}
		// 记录异常日志
		catch (SealblockException e) {
			log.error(methodName + "Err[" + tagId + "](" + (new Date().getTime() - startTime) + ")" + e.getMessage());
			throw e;
		}

		return result;
	}

	/**
	 * @param commandData
	 * @param address
	 * @param timeout
	 * @return
	 * @throws SealblockException
	 */
	private static Object postRemote(byte[] commandData, String address, long timeout) throws SealblockException {
		final ZMQ.Context context = ZMQ.context(1);
		final ZMQ.Socket socket = context.socket(ZMQ.REQ);
		try {
			socket.setSendTimeOut(0);
			socket.setLinger(0);
			socket.connect(address);

			Object res = null;
			if (socket.send(commandData, 0)) {
				final String threadName = Thread.currentThread().getName();
				FutureTask<Object> future = new FutureTask<Object>(new Callable<Object>() {
					public Object call() {
						Thread.currentThread().setName(threadName);
						Object ret = null;

						byte[] res = socket.recv(0);
						try {
							ret = new ObjectMapper(new MessagePackFactory()).readValue(res, new TypeReference<List<Object>>() {
							});
						}
						//
						catch (Exception e) {
							log.error("WrongZeroFormatResp[]" + e.getMessage(), e);
							ret = new SealblockResponseException("解析返回数据异常", e);
						}
						return ret;
					}
				});
				executor.execute(future);

				res = future.get(timeout, TimeUnit.SECONDS);
			}
			else {
				throw new SealblockRequestException("发送请求异常，请检查通讯连接");
			}

			if (res instanceof SealblockException) {
				throw (SealblockException) res;
			}
			else {
				return res;
			}
		}
		// 加入通讯外发队列失败
		catch (RejectedExecutionException e) {
			throw new SealblockRequestException("网络繁忙，连接池已满", e);
		}
		// 读取数据超时
		catch (TimeoutException e) {
			throw new SealblockResponseException("等待数据返回超时", e);
		}
		// 已识别异常
		catch (SealblockRequestException e) {
			throw e;
		}
		// 未识别异常
		catch (Exception e) {
			throw new SealblockException(e.getMessage(), e);
		}
		// 释放数据
		finally {
			if (socket != null) {
				socket.close();
			}
			if (context != null) {
				context.term();
			}
		}
	}

	/**
	 * @param itemObj
	 * @return
	 * @throws SealblockException
	 */
	private static Object processResult(Object itemObj) throws SealblockException {
		// 非List对象或数组位数不对
		if (!(itemObj instanceof List) || ((List<?>) itemObj).size() < 3) {
			throw new SealblockResponseException("返回数据报文格式错误");
		}

		List<?> items = (List<?>) itemObj;

		// 提取返回状态
		String status = (String) items.get(1);

		if ("Ok".equalsIgnoreCase(status)) {
			Object rel = items.get(2);
			if (rel instanceof List) {
				rel = ((List<?>) rel).get(0);
			}
			return rel;
		}
		// 解析异常时报文
		else {
			Object errObj = items.get(2);
			if (errObj instanceof List && ((List<?>) errObj).size() >= 3) {
				List<?> errMsgs = (List<?>) errObj;
				throw new SealblockResponseException((String) errMsgs.get(1), new Exception((String) errMsgs.get(2)));
			}
			// 无法解析结构时
			else {
				throw new SealblockResponseException("返回错误，未识别错误信息", new Exception(JSON.toJSONString(errObj)));
			}
		}
	}
}
