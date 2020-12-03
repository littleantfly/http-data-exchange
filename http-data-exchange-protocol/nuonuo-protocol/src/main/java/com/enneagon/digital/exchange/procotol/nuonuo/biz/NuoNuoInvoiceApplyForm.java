package com.enneagon.digital.exchange.procotol.nuonuo.biz;

import lombok.Data;

import java.util.List;

/**
 * InvoiceApply
 *
 * @author Jim
 * @version 1.0
 * @date 2019/9/20 17:40
 */
@Data
public class NuoNuoInvoiceApplyForm {

    private String buyername;

    private String taxnum;

    private String phone;

    private String address;

    private String account;

    private String telephone;

    private String orderno;

    private String invoicedate;

    private String clerk;

    private String saleaccount;

    private String salephone;

    private String saleaddres;

    private String saletaxnum;

    private String kptype;

    private String message;

    private String payee;

    private String checker;

    private String fpdm;

    private String fphm;

    private String tsfs;

    private String email;

    private String qdbz;

    private String qdxmmc;

    private String dkbz;

    private String deptid;

    private String clerkid;

    private String invoiceLine;

    private String cpybz;

    private String billInfoNo;

    private List<GoodDetailForm> detail;

    private String goodsname;

    private String num;

    private String price;

    private String hsbz;

    private String taxrate;

    private String spec;

    private String unit;

    private String spbm;

    private String zsbm;

    private String fphxz;

    private String yhzcbs;

    private String zzstsgl;

    private String lslbs;

    private String kce;

    private String taxfreeamt;

    private String tax;

    private String taxamt;
}
