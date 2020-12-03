package com.enneagon.digital.exchange.procotol.chinapay.biz;

import lombok.Data;

/**
 * ChinapayQueryInvoiceResponseBody
 *
 * @author Jim
 * @version 1.0
 * @date 2019/8/13 16:55
 */
@Data
public class ChinaPayInvoiceQueryVO {

    private String qrCode;

    private String respCode;

    private String respMsg;

    private String bgRetUrl;

    private String pdfUrl;

    private String issueDate;
}
