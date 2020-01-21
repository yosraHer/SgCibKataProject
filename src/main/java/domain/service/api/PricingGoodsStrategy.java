package domain.service.api;


import domain.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

public interface PricingGoodsStrategy {
    public BigDecimal getTotalPrice(Product product, int quantity);
}
