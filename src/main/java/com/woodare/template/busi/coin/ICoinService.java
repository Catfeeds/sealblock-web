package com.woodare.template.busi.coin;

import com.woodare.framework.exception.MessageWooException;
import com.woodare.sealblock.data.TransactionData;

/**
 * @author Luke
 */
public interface ICoinService {

	/**
	 * @param transaction
	 * @param isChargeFlag
	 * @return
	 * @throws MessageWooException
	 */
	boolean approveTrans(TransactionData transaction, boolean isChargeFlag) throws MessageWooException;
}
