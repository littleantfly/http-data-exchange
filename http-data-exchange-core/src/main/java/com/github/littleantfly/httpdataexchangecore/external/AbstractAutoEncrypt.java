package com.github.littleantfly.httpdataexchangecore.external;

import cn.hutool.core.util.StrUtil;
import com.github.littleantfly.httpdataexchangecore.annotation.AutoEncrypt;
import com.github.littleantfly.httpdataexchangecore.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractAutoEncrypt {

    private HttpSecurityContext securityService;

    protected boolean support(AutoEncrypt annotation) {
        boolean support = (annotation!=null);
        if (log.isDebugEnabled()) {
            log.debug("Determine whether the method needs to be decrypted: {}", support);
        }
        if(!support) {
            return false;
        }
        String name = annotation.name();
        Class<? extends HttpSecurityContext> clazz = annotation.value();
        if(StrUtil.isNotBlank(name)) {
            securityService = SpringContextUtil.getBean(name, clazz);
        } else {
            securityService = SpringContextUtil.getBean(clazz);
        }
        return true;
    }

    public HttpSecurityContext getSecurityService() {
        return securityService;
    }
}
