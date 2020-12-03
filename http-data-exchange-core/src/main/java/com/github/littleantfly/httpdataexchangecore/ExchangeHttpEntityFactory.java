package com.github.littleantfly.httpdataexchangecore;

import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * @author littl
 */
public class ExchangeHttpEntityFactory {

    public static HttpEntity<?> getHttpEntity(ExchangeRequest request, HttpHeaders httpHeaders) {

        switch (httpHeaders.getContentType().toString()) {
            case MediaType.APPLICATION_JSON_VALUE:
                return new JsonExchangeHttpEntityHandler().getHttpEntity(request, httpHeaders);
            case MediaType.APPLICATION_FORM_URLENCODED_VALUE:
                return new FormExchangeHttpEntityHandler().getHttpEntity(request, httpHeaders);
        }
        return null;
    }
}
