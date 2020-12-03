package com.enneagon.digital.exchange.procotol.chinapay.biz;

import lombok.Data;

/**
 * ChinapayQueryInvoiceResponseBody
 *
 * @author Jim
 * @version 1.0
 * @date 2019/8/13 16:54
 */
@Data
public class ChinaPayInvoiceQueryForm {

    private String merNo;

    private String busiType = "2006";

    private String merOrderDate;

    private String merOrderId;

}
