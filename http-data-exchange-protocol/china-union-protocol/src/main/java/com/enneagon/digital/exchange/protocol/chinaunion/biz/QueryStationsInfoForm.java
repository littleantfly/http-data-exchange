package com.enneagon.digital.exchange.protocol.chinaunion.biz;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

/**
 * 查询充电站信息
 *
 * @author Shengbang.Jiang
 * @since 2019-07-15
 */
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class QueryStationsInfoForm {

    private String LastQueryTime;

    private Integer PageNo = 1;

    private Integer PageSize = 10;
}