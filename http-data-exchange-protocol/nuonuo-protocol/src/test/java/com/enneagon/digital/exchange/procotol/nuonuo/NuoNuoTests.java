package com.enneagon.digital.exchange.procotol.nuonuo;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.enneagon.digital.exchange.procotol.nuonuo.biz.GoodDetailForm;
import com.enneagon.digital.exchange.procotol.nuonuo.biz.NuoNuoInvoiceApplyForm;
import com.enneagon.digital.exchange.procotol.nuonuo.biz.NuoNuoInvoiceApplyVO;
import com.enneagon.digital.exchange.procotol.nuonuo.config.NuoNuoConfig;
import com.github.littleantfly.httpdataexchangecore.impl.EncryptExchangeHandlerWrapper;
import com.github.littleantfly.httpdataexchangecore.impl.PerpetualExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
public class NuoNuoTests {

    private EncryptExchangeHandlerWrapper handler;

    NuoNuoInvoiceApplyForm form = null;

    @Before
    public void init() {
        handler = new EncryptExchangeHandlerWrapper(new PerpetualExchangeHandler(new RestTemplate()), new NuoNuoHttpEncryptServiceImpl());

        form = new NuoNuoInvoiceApplyForm();
        form.setBuyername("浙江爱信诺");
        form.setTaxnum("124511234993295177");
        form.setPhone("18521084140");
        form.setAddress("浙江省杭州市万塘路");
        form.setAccount("");
        form.setTelephone("18521084140");
        form.setOrderno("nuon11uo12345127");
        form.setInvoicedate("2020-07-01 00:00:00");
        form.setClerk("开票员");
        form.setSaletaxnum("339901999999142");
        form.setKptype("1");
        form.setClerk("系统");
        form.setMessage("");
        form.setSaleaccount("宇宙行442612010103507108");
        form.setTsfs("-1");
        form.setEmail("172259412@qq.com");
        form.setQdbz("0");

        ArrayList<GoodDetailForm> goodDetails = new ArrayList<>();

        GoodDetailForm gd = new GoodDetailForm();
        gd.setGoodsname("商品名称");
        gd.setNum("1");
        gd.setUnit("个");
        gd.setPrice("100");
        gd.setHsbz("1");

        gd.setTaxrate("0.13");

        gd.setFphxz("0");
        gd.setFphxz("0");
        gd.setSpbm("10101150101");
        goodDetails.add(gd);

        form.setDetail(goodDetails);
    }

    @Test
    public void test() {

        NuoNuoRequest nuoNuoRequest = new NuoNuoRequest();
        Map<String, Object> plaintext = MapUtil.newHashMap();
        plaintext.put("order", form);
        plaintext.put("identity", "2329CC5F90EDAA8208F1F3C72A0CE72A713A9D425CD50CDE");
        nuoNuoRequest.setOrder(JSON.toJSONString(plaintext));

        ExchangeResponse res = handler.post("http://localhost/cxfServerKpOrderSync.action", nuoNuoRequest);

        log.info(JSON.toJSONString(res));

    }

    @Test
    public void NuoNuoServiceTest(){
        NuoNuoConfig nuoNuoConfig = new NuoNuoConfig();
        nuoNuoConfig.setIdentity("2329CC5F90EDAA8208F1F3C72A0CE72A713A9D425CD50CDE");
        nuoNuoConfig.setUrlPrefix("http://localhost");
        NuoNuoService nuoNuoService = new NuoNuoService(nuoNuoConfig);

        NuoNuoInvoiceApplyVO nuoNuoInvoiceVO = nuoNuoService.applyInvoice(form);
        log.info(JSON.toJSONString(nuoNuoInvoiceVO));

    }

}
