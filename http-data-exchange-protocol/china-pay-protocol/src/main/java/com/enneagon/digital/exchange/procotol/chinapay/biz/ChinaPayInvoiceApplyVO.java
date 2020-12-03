package com.enneagon.digital.exchange.procotol.chinapay.biz;

import lombok.Data;


/**
 * ChinapayIssueInvoiceResponseBody
 *
 * @author Jim
 * @version 1.0
 * @date 2019/7/15 09:15
 */
@Data
public class ChinaPayInvoiceApplyVO {

    private String qrCode;

    private String respCode;

    private String respMsg;
}
