package com.woodare.sealblock.util;

/**
 * @author Luke
 */
public class SealblockResponseException extends SealblockException {
	private static final long serialVersionUID = 4541477171273085595L;

	/**
	 * @param message
	 */
	public SealblockResponseException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param throwable
	 */
	public SealblockResponseException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
