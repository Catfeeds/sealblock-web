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
package com.woodare.template.busi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.core.jpa.model.SystemProperties;
import com.woodare.core.jpa.model.SystemUser;
import com.woodare.core.jpa.model.data.EnumUserRole;
import com.woodare.core.jpa.service.ISystemPropertiesDAO;
import com.woodare.core.jpa.service.ISystemUserDAO;
import com.woodare.core.util.SDFFactory;
import com.woodare.core.web.startup.InitializeDatabase.IInitializer;
import com.woodare.framework.model.data.EnumDeleteStatus;
import com.woodare.framework.utils.JavaMD5Hash;
import com.woodare.framework.utils.SysProperties;
import com.woodare.template.constant.SystemPropertiesConstant;
import com.woodare.template.jpa.model.DownMerchant;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.model.PasswayRouteMerchant;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumFundAccountType;
import com.woodare.template.jpa.model.data.EnumMercCategory;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;
import com.woodare.template.jpa.persistence.persistence.IPasswayRouteMerchantDAO;

/**
 * Please describe
 * 
 * @version 1.0
 * @createDate 2013-6-11 下午5:55:39
 * @author lu_feng
 */
@Service
public class InitializeDataService implements IInitializer {
	private final Logger log = Logger.getLogger(InitializeDataService.class);

	@Transactional(propagation = Propagation.REQUIRED)
	public void execute() {
		init();
	}

	@Autowired
	private ISystemUserDAO systemUserDAO;
	@Autowired
	private IDownMerchantDAO downMerchantDAO;
	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;
	@Autowired
	private ISystemPropertiesDAO systemPropertiesDAO;
	@Autowired
	private IPasswayRouteMerchantDAO passwayRouteMerchantDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public void init() {
		List<SystemProperties> systemProperties = systemPropertiesDAO.findAll();

		if (systemProperties == null || systemProperties.size() <= 0) {
			log.info("First time to initialize datbase");
			try {
				initUsers();

				initPasswayRouteMerchants();

				systemProperties = initSystemProperties();

			} catch (Exception e) {
				log.error("Can not init SystemProperties, exit -1", e);
				System.exit(-1);
			}
		}

		try {
			upgradeSystemProperties(systemProperties);
		} catch (Exception e) {
			log.error("Can not upgrade SystemProperties, exit -1", e);
			System.exit(-1);
		}
	}

	private void upgradeSystemProperties(List<SystemProperties> properties) {
		@SuppressWarnings("unused")
		SystemProperties propversion = null;
		for (SystemProperties prop : properties) {
			if (prop.getPcode().equals(SystemPropertiesConstant.CODE_SYSTEM_VERSION)) {
				propversion = prop;
				break;
			}
		}
	}

	/**
	 * 
	 */
	private void initPasswayRouteMerchants() {
		List<PasswayRouteMerchant> merchants = passwayRouteMerchantDAO.findAll();
		if (merchants == null || merchants.size() == 0) {
			// 系统管理员
			PasswayRouteMerchant merchant = new PasswayRouteMerchant();
			/** 公链类型 **/
			merchant.setChannel(EnumDownNoCardChannel.Ethereum);
			/** 币标识, eth, usdt */
			merchant.setCoin("ETH");
			/** 币名称 */
			merchant.setCoinName("以太币");
			/** 精度位数 */
			merchant.setPriceScale(5);
			/** 签名KEY密文 */
			merchant.setSignKey("");
			/** 加密KEY密文 */
			merchant.setEncKey("");
			/** 额外参数 **/
			merchant.setRemark("暂无");
			/** 信息,feeText **/
			/** 开始时间 **/
			merchant.setStartTime("000000");
			/** 结束时间 **/
			merchant.setEndTime("000000");
			/** precision交易额 */
			merchant.setMinPerAmt("0.0001");
			/** 单笔最大额 */
			merchant.setMaxPerAmt("10");
			/** 日累计交易额 */
			merchant.setMaxTotAmt("100000");
			/** 状态 */
			merchant.setStatus(EnumDownUserStatus.ACTIVE);

			this.passwayRouteMerchantDAO.save(merchant);

			merchant = new PasswayRouteMerchant();
			merchant.setChannel(EnumDownNoCardChannel.Ethereum);
			merchant.setCoin("BTC");
			merchant.setCoinName("比特币");
			merchant.setPriceScale(8);
			merchant.setSignKey("");
			merchant.setEncKey("");
			merchant.setRemark("暂无");
			/** 信息,feeText **/
			merchant.setStartTime("000000");
			merchant.setEndTime("000000");
			merchant.setMinPerAmt("0.000001");
			merchant.setMaxPerAmt("2");
			merchant.setMaxTotAmt("10000");
			merchant.setStatus(EnumDownUserStatus.ACTIVE);
			this.passwayRouteMerchantDAO.save(merchant);
		}
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private List<SystemProperties> initSystemProperties() throws Exception {
		SysProperties properties = SysProperties.getInstance();

		List<SystemProperties> allSystemProperties = new ArrayList<SystemProperties>();

		SystemUser admin = this.systemUserDAO.findByUsername(SysProperties.getInstance().getProperty(SystemPropertiesConstant.CODE_SUPER_ACCOUNTNAME));

		Date date = new Date();

		// 站点根访问路径
		SystemProperties systemProperties = new SystemProperties();
		systemProperties.setPcode(SystemPropertiesConstant.CODE_ROOT_URL);
		systemProperties.setPvalue(properties.getProperty(SystemPropertiesConstant.CODE_ROOT_URL, "http://www.woodare.com"));
		systemProperties.setPname("站点根路径");
		systemProperties.setPdesc("需根据实际部署的服务器访问路径进行调整，否则将无法正常访问本站");
		systemProperties.setSortNum(1);
		systemProperties.setCreatorId(admin.getUsername());
		systemProperties.setCreateDate(date);
		systemProperties.setIsEditFlag(true);
		systemProperties.setDeleteStatus(EnumDeleteStatus.VALID);
		systemPropertiesDAO.save(systemProperties);
		allSystemProperties.add(systemProperties);

		// 静态资源文件访问路径
		systemProperties = new SystemProperties();
		systemProperties.setPcode(SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL);
		systemProperties.setPvalue(properties.getProperty(SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://www.woodare.com"));
		systemProperties.setPname("静态文件访问地址");
		systemProperties.setPdesc("CSS、JS、图片等静态文件访问地址");
		systemProperties.setSortNum(2);
		systemProperties.setCreatorId(admin.getUsername());
		systemProperties.setCreateDate(date);
		systemProperties.setIsEditFlag(true);
		systemProperties.setDeleteStatus(EnumDeleteStatus.VALID);
		systemPropertiesDAO.save(systemProperties);

		// 站点别名（公开）访问根路径
		systemProperties = new SystemProperties();
		systemProperties.setPcode(SystemPropertiesConstant.CODE_BASE_URL_ALIAS);
		systemProperties.setPvalue(properties.getProperty(SystemPropertiesConstant.CODE_BASE_URL_ALIAS, "http://www.woodare.com"));
		systemProperties.setPname("站点别名（公开）路径");
		systemProperties.setPdesc("对外公开访问使用的域名路径地址信息");
		systemProperties.setSortNum(3);
		systemProperties.setCreatorId(admin.getUsername());
		systemProperties.setCreateDate(date);
		systemProperties.setIsEditFlag(true);
		systemProperties.setDeleteStatus(EnumDeleteStatus.VALID);
		systemPropertiesDAO.save(systemProperties);
		allSystemProperties.add(systemProperties);

		// 后台程序版本号
		systemProperties = new SystemProperties();
		systemProperties.setPcode(SystemPropertiesConstant.CODE_SYSTEM_VERSION);
		systemProperties.setPvalue("1.0.001");
		systemProperties.setPname("后台服务器版本信息");
		systemProperties.setPdesc("");
		systemProperties.setSortNum(99);
		systemProperties.setCreatorId(admin.getUsername());
		systemProperties.setCreateDate(date);
		systemProperties.setDeleteStatus(EnumDeleteStatus.VALID);
		systemProperties.setIsEditFlag(false);
		systemPropertiesDAO.save(systemProperties);
		allSystemProperties.add(systemProperties);

		return allSystemProperties;
	}

	/**
	 * 
	 */
	private void initUsers() {
		List<SystemUser> users = systemUserDAO.findAll();
		if (users == null || users.size() == 0) {
			// 系统管理员
			SystemUser user = new SystemUser();
			user.setEmail(SysProperties.getInstance().getProperty(SystemPropertiesConstant.CODE_SUPER_ACCOUNTNAME));
			user.setUsername(SysProperties.getInstance().getProperty(SystemPropertiesConstant.CODE_SUPER_ACCOUNTNAME));
			user.setPassword(JavaMD5Hash.md5(SysProperties.getInstance().getProperty(SystemPropertiesConstant.CODE_SUPER_ACCOUNTPASSWORD)));
			user.setCreateDate(new Date());
			user.setUserRole(EnumUserRole.SUPERVISOR);
			user.setIsAdminFlag(true);
			this.systemUserDAO.save(user);

			DownMerchant downMerchant = new DownMerchant();
			downMerchant.setMercCategory(EnumMercCategory.NONE);
			downMerchant.setMchNo("T0001");
			downMerchant.setName("机构测试A");
			downMerchant.setEncKey("1234567812345678");
			downMerchant.setSignKey("1234");
			/** 代付KEY, payKey */
			downMerchant.setLimitIps("127.0.0.1");
			downMerchant.setEnableNotify(true);
			downMerchant.setEnableBalChgFlg(false);
			downMerchant.setSupportCoin("ETH,BTC");
			downMerchant.setCreditRatio(100l);
			downMerchant.setSettleDate(SDFFactory.DATE.format(new Date()));
			this.downMerchantDAO.save(downMerchant);

			DownMerchantFundAccount fundModel = createByMerchant(downMerchant);
			fundModel.setCoin("ETH");
			fundModel.setCoinName("以太币");
			fundModel.setFeeRatio(new BigDecimal("1"));
			this.downMerchantFundAccountDAO.save(fundModel);
			fundModel = createByMerchant(downMerchant);
			fundModel.setCoin("BTC");
			fundModel.setCoinName("比特币");
			fundModel.setFeeRatio(new BigDecimal("0.2"));
			this.downMerchantFundAccountDAO.save(fundModel);

			downMerchant = new DownMerchant();
			downMerchant.setMercCategory(EnumMercCategory.NONE);
			downMerchant.setMchNo("T0002");
			downMerchant.setName("机构测试B");
			downMerchant.setEncKey("1234567812345678");
			downMerchant.setSignKey("1234");
			/** 代付KEY, payKey */
			downMerchant.setLimitIps("127.0.0.1");
			downMerchant.setEnableNotify(true);
			downMerchant.setEnableBalChgFlg(false);
			downMerchant.setSupportCoin("ETH,BTC");
			downMerchant.setCreditRatio(100l);
			downMerchant.setSettleDate(SDFFactory.DATE.format(new Date()));

			this.downMerchantDAO.save(downMerchant);
			fundModel = createByMerchant(downMerchant);
			fundModel.setCoin("ETH");
			fundModel.setCoinName("以太币");
			fundModel.setFeeRatio(new BigDecimal("1"));
			this.downMerchantFundAccountDAO.save(fundModel);
			fundModel = createByMerchant(downMerchant);
			fundModel.setCoin("BTC");
			fundModel.setCoinName("比特币");
			fundModel.setFeeRatio(new BigDecimal("0.2"));
			this.downMerchantFundAccountDAO.save(fundModel);
		}
	}

	private DownMerchantFundAccount createByMerchant(DownMerchant downMerchant) {
		DownMerchantFundAccount fundModel = new DownMerchantFundAccount();
		fundModel.setAccountType(EnumFundAccountType.Merchant);
		fundModel.setMchNo(downMerchant.getMchNo());
		fundModel.setMchName(downMerchant.getName());
		fundModel.setFeeRatio(new BigDecimal("1"));
		fundModel.setAddFeeAmt(new BigDecimal("0"));
		fundModel.setStatus(EnumDownUserStatus.ACTIVE);
		fundModel.setLastSettleAmt(new BigDecimal("0"));
		fundModel.setSettleInAmt(new BigDecimal("0"));
		fundModel.setSettleOutAmt(new BigDecimal("0"));
		fundModel.setFrozenAmt(new BigDecimal("0"));
		fundModel.setCurInAmt(new BigDecimal("0"));
		fundModel.setCurOutAmt(new BigDecimal("0"));
		fundModel.setChangeDate(downMerchant.getSettleDate());
		return fundModel;
	}

	/**
	 * 
	 */
	public void destroy() {
		log.info("Destroy");
	}
}
