package com.enneagon.digital.exchange.procotol.chinapay.biz;

import lombok.Data;

/**
 * 开票请求
 *
 * @author Jim
 * @version 1.0
 * @date 2019/8/7 13:20
 */
@Data
public class ChinaPayInvoiceApplyForm {

    private String merNo;

    private String busiType = "1028";

    private String orderId;

    private String orderDate;

    private String merOrderId;

    private String merOrderDate;

    private String buyerName;

    private String buyerTaxCode;

    private String buyerAddress;

    private String buyerTelephone;

    private String buyerBank;

    private String buyerAccount;

    private String amount;

    private String goodsDetail;

    private String remark;

    private String notifyMobileNo;

    private String notifyEMail;

    private String merResv;

}
