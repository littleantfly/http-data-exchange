package com.enneagon.digital.exchange.procotol.nuonuo;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.enneagon.digital.exchange.procotol.nuonuo.biz.NuoNuoInvoiceApplyForm;
import com.enneagon.digital.exchange.procotol.nuonuo.biz.NuoNuoInvoiceApplyVO;
import com.enneagon.digital.exchange.procotol.nuonuo.config.NuoNuoConfig;
import com.github.littleantfly.httpdataexchangecore.wrapper.EncryptExchangeHandlerWrapper;
import com.github.littleantfly.httpdataexchangecore.wrapper.PerpetualExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.model.EncryptResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author littl
 */
@Slf4j
public class NuoNuoService {

    private final NuoNuoConfig nuoNuoConfig;

    public NuoNuoService(NuoNuoConfig nuoNuoConfig) {
        this.nuoNuoConfig = nuoNuoConfig;
    }

    private static final EncryptExchangeHandlerWrapper HANDLER = new EncryptExchangeHandlerWrapper(new PerpetualExchangeHandler(new RestTemplate()), new NuoNuoHttpEncryptServiceImpl());

    public NuoNuoInvoiceApplyVO applyInvoice(NuoNuoInvoiceApplyForm form) {
        NuoNuoRequest nuoNuoRequest = new NuoNuoRequest();
        Map<String, Object> plaintext = MapUtil.newHashMap();
        plaintext.put("order", form);
        plaintext.put("identity", nuoNuoConfig.getIdentity());
        nuoNuoRequest.setOrder(JSON.toJSONString(plaintext));
        EncryptResponse res = (EncryptResponse)HANDLER.post(nuoNuoConfig.getUrlPrefix()+"/shop/buyer/allow/cxfKp/cxfServerKpOrderSync.action", nuoNuoRequest);
        return JSON.parseObject(res.getResponseBody(), NuoNuoInvoiceApplyVO.class);
    }

}
