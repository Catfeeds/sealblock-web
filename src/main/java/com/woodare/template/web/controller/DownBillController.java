/******************************************************************************
 *                                                                             
 *                      Woodare PROPRIETARY INFORMATION                        
 *                                                                             
 *          The information contained herein is proprietary to Woodare         
 *           and shall not be reproduced or disclosed in whole or in part      
 *                    or used for any design or manufacture                    
 *              without direct written authorization from FengDa.              
 *                                                                             
 *            Copyright (c) 2013 by Woodare.  All rights reserved.             
 *                                                                             
 *****************************************************************************/
package com.woodare.template.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.woodare.core.base.BaseController;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.utils.Base64Utils;
import com.woodare.framework.utils.FileCommonUtils;
import com.woodare.framework.utils.StringUtils;

/**
 * ClassName: MFPageController
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Jul 22, 2015
 * 
 */
@Controller
@RequestMapping("/down/bill")
public class DownBillController extends BaseController {

	@RequestMapping(value = "/file/{encodeDate}/{encodePath}")
	@Transactional(propagation = Propagation.REQUIRED)
	public String page(@PathVariable String encodeDate, @PathVariable String encodePath, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dateStr = new String(Base64Utils.decode(encodeDate));
		String error = "";
		try {
			Date d = SDFFactory.DATETIME.parse(dateStr);
			if (System.currentTimeMillis() - d.getTime() >= 60000) {
				error = "已超时，请重新请求下载地址";
			}
		} catch (ParseException e) {
			error = "时间不正确";
		}
		if (StringUtils.isNotEmpty(error)) {
			response.getOutputStream().write(error.getBytes());
			return null;
		}

		String path = new String(Base64Utils.decode(encodePath));

		String detailPath = FileCommonUtils.getFullPath("uploads", path);
		File f = new File(detailPath);
		response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
		return "forward:/uploads/" + path;
	}
}
