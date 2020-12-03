package com.github.littleantfly.httpdataexchangecore;

import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public interface ExchangeHttpEntityHandler<T> {

    HttpEntity<T> getHttpEntity(ExchangeRequest request, HttpHeaders httpHeaders);
}
