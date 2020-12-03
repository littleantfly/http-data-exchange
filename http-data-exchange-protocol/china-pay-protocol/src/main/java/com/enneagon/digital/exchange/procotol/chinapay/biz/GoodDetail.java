package com.enneagon.digital.exchange.procotol.chinapay.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GoodDetail
 *
 * @author Jim
 * @version 1.0
 * @date 2019/8/8 14:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodDetail {

    private Integer index;

    private String attribute;

    private Integer discountIndex;

    private String name;

    private Double priceIncludingTax;

    private Integer taxRate;

    private Double quantity;

    private String unit;

    private String sn;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer index;
        private String attribute;
        private Integer discountIndex;
        private String name;
        private Double priceIncludingTax;
        private Integer taxRate;
        private Double quantity;
        private String unit;
        private String sn;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder index(final Integer index) {
            this.index = index;
            return this;
        }

        public Builder attribute(final String attribute) {
            this.attribute = attribute;
            return this;
        }

        public Builder discountIndex(final Integer discountIndex) {
            this.discountIndex = discountIndex;
            return this;
        }

        public Builder priceIncludingTax(final Double priceIncludingTax) {
            this.priceIncludingTax = priceIncludingTax;
            return this;
        }

        public Builder taxRate(final Integer taxRate) {
            this.taxRate = taxRate;
            return this;
        }

        public Builder quantity(final Double quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder unit(final String unit) {
            this.unit = unit;
            return this;
        }

        public Builder sn(final String sn) {
            this.sn = sn;
            return this;
        }

        public GoodDetail build() {
            return new GoodDetail(index, attribute, discountIndex, name, priceIncludingTax, taxRate, quantity, unit, sn);
        }
    }
}
