package domain.service.api;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.TreeMap;

public interface PricingGoodsService {
    public BigDecimal getTotalAmountGoods(TreeMap<String, Integer> goodsToBuy);
}
