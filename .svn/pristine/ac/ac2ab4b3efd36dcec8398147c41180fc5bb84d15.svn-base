package com.woodare.sealblock.util;

import com.woodare.framework.exception.MessageWooException;

/**
 * @author Luke
 */
public class SealblockException extends MessageWooException {
	private static final long serialVersionUID = 4541477171273085595L;

	/**
	 * @param message
	 */
	public SealblockException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param throwable
	 */
	public SealblockException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public String getFriendMessage() {
		String msg = this.getMessage();

		if (msg != null) {
			// TODO:可以配置话
			if ("Returned error: insufficient funds for gas * price + value".equals(msg)) {
				msg = "余额不足";
			}
			else {
				msg = "处理异常，需确认原因";
			}
		}

		return msg;
	}
}
