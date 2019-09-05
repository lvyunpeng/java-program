package com.lyp.sample;

import lombok.Data;

/**
 * @author : dongjunpeng
 * @date : 2019/4/25 12:02
 */
@Data
public class GroupChatPriceConfig {

    // 1: diamond 2.gold
    private Integer currency;

    private Integer price;

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
