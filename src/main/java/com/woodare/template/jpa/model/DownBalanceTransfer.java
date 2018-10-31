package com.woodare.template.jpa.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumOrderStatus;

/**
 * ClassName: DownBalanceTransfer
 * 
 * @description
 * @author xue.songli
 * @Date Oct. 31, 2018
 */
@Entity
@EntityDescriptor(name = "转账交易", category = "content")
public class DownBalanceTransfer extends AbstractBusiModel {
    private static final long serialVersionUID = 2788988110132939550L;

    /** 公链类型 **/
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumDownNoCardChannel channel;

    /** 币标识, eth, usdt */
    @Column(length = 10, nullable = false)
    private String coin;

    /** 币名称 */
    @Column(length = 20, nullable = false)
    private String coinName;

    /** 代理商 **/
    @Column(length = 20, nullable = false)
    private String agentNo;

    /** 机构号 **/
    @Column(name = "mch_no", length = 20, nullable = false)
    private String mchNo;

    /** 机构名称 **/
    @Column(length = 100, nullable = false)
    private String mchName;

    /** 交易用户标识 */
    @Column(length = 32, nullable = false)
    private String userNo;

    /** 平台流水号 */
    @Column(length = 30, nullable = false, unique = true)
    private String transNo;

    /** 平台日期yyyyMMdd */
    @Column(length = 8, nullable = false)
    private String transDate;

    /** 下游交易流水号 */
    @Column(name = "trade_no", length = 40, nullable = false)
    private String tradeNo;

    /** 下游交易时间, 格式：yyyyMMddHHmmss */
    @Column(length = 14, nullable = false)
    private String orderDate;

    /** 下游异步通知地址 */
    @Column(length = 512)
    private String notifyUrl;

    /** 下游接收方货币地址 */
    @Column(length = 128, nullable = false)
    private String receivCoinAddr;

    /** 交易额 */
    @Column(scale = 8, precision = 15, nullable = false)
    private BigDecimal price;

    /** 交易备注信息 */
    @Column(length = 50, nullable = false)
    private String remark;

    /** 交易矿工费 */
    @Column(scale = 8, precision = 15, nullable = false)
    private BigDecimal subUserFee;

    /** 用户货币地址 */
    @Column(length = 128, nullable = false)
    private String userCoinAddr;

    /** 用户姓名 */
    @Column(length = 30, nullable = false)
    private String userName;

    /** 用户身份证号 */
    @Column(length = 30, nullable = false)
    private String userCertId;

    /** 用户联系电话 */
    @Column(length = 20, nullable = false)
    private String userPhone;

    /** 保留缺省域1 */
    @Column(length = 100)
    private String merResv1;

    /** 保留缺省域2 */
    @Column(length = 200)
    private String merResv2;

    /** 上游流水号 */
    @Column(length = 128)
    private String upTransNo;

    /** 交易状态 */
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private EnumOrderStatus status;

    /** 交易状态描述 */
    @Column(length = 128)
    private String statusDesc;

    /** 状态更新时间 */
    @Column(nullable = false)
    private Date statusChgDate;

    /** 公网交易状态 */
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private EnumOrderStatus fundStatus;

    /** 公网状态描述 */
    @Column(length = 128)
    private String fundStatusDesc;

    /** 公网更新时间 */
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fundChgDate;

    public EnumDownNoCardChannel getChannel() {
        return channel;
    }

    public void setChannel(EnumDownNoCardChannel channel) {
        this.channel = channel;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public String getReceivCoinAddr() {
        return receivCoinAddr;
    }

    public void setReceivCoinAddr(String receivCoinAddr) {
        this.receivCoinAddr = receivCoinAddr;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getSubUserFee() {
        return subUserFee;
    }

    public void setSubUserFee(BigDecimal subUserFee) {
        this.subUserFee = subUserFee;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserCoinAddr() {
        return userCoinAddr;
    }

    public void setUserCoinAddr(String userCoinAddr) {
        this.userCoinAddr = userCoinAddr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCertId() {
        return userCertId;
    }

    public void setUserCertId(String userCertId) {
        this.userCertId = userCertId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getMerResv1() {
        return merResv1;
    }

    public void setMerResv1(String merResv1) {
        this.merResv1 = merResv1;
    }

    public String getMerResv2() {
        return merResv2;
    }

    public void setMerResv2(String merResv2) {
        this.merResv2 = merResv2;
    }

    public String getUpTransNo() {
        return upTransNo;
    }

    public void setUpTransNo(String upTransNo) {
        this.upTransNo = upTransNo;
    }

    public EnumOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EnumOrderStatus status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Date getStatusChgDate() {
        return statusChgDate;
    }

    public void setStatusChgDate(Date statusChgDate) {
        this.statusChgDate = statusChgDate;
    }

    public EnumOrderStatus getFundStatus() {
        return fundStatus;
    }

    public void setFundStatus(EnumOrderStatus fundStatus) {
        this.fundStatus = fundStatus;
    }

    public String getFundStatusDesc() {
        return fundStatusDesc;
    }

    public void setFundStatusDesc(String fundStatusDesc) {
        this.fundStatusDesc = fundStatusDesc;
    }

    public Date getFundChgDate() {
        return fundChgDate;
    }

    public void setFundChgDate(Date fundChgDate) {
        this.fundChgDate = fundChgDate;
    }
}
