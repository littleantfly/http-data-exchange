package com.enneagon.digital.exchange.procotol.nuonuo.biz;

import lombok.Data;

/**
 * @author littl
 */
@Data
public class NuoNuoInvoiceApplyVO {

    private String status;

    private String message;

    /**
     * 发票请求流水号
     */
    private String fpqqlsh;
}
